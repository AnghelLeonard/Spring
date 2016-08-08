package test.ol;

import com.vladmihalcea.concurrent.aop.OptimisticConcurrencyControlAspect;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import net.ttddyy.dsproxy.listener.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import sh.service.ProductService;
import sh.service.ProductServiceImpl;

/**
 *
 * @author Anghel Leonard
 */
@Configuration
@EnableJpaRepositories(basePackages = {
        "sh.dao"
})
@PropertySource({"classpath:META-INF/jdbc-mysql.properties"})
@ComponentScan(basePackages = "sh.controllers")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SHConfigurationTest {

    public static final String DATA_SOURCE_PROXY_NAME = "DSProxyTest";

    @Value("${jdbc.dataSourceClassName}")
    private String dataSourceClassName;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUser;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }
       
    @Bean
    public OptimisticConcurrencyControlAspect optimisticConcurrencyControlAspect() {
        return new com.vladmihalcea.concurrent.aop.OptimisticConcurrencyControlAspect();
    }          

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "close")
    public DataSource actualDataSource() {
        Properties driverProperties = new Properties();
        driverProperties.setProperty("url", jdbcUrl);
        driverProperties.setProperty("user", jdbcUser);
        driverProperties.setProperty("password", jdbcPassword);

        Properties properties = new Properties();
        properties.put("dataSourceClassName", dataSourceClassName);
        properties.put("dataSourceProperties", driverProperties);
        properties.setProperty("maximumPoolSize", String.valueOf(3));
        return new HikariDataSource(new HikariConfig(properties));
    }

    @Bean
    public DataSource dataSource() {
        SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
        loggingListener.setQueryLogEntryCreator(new DefaultQueryLogEntryCreator());
        return ProxyDataSourceBuilder
                .create(actualDataSource())
                .name(DATA_SOURCE_PROXY_NAME)
                .listener(loggingListener)
                .countQuery()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPersistenceUnitName(getClass().getSimpleName());
        localContainerEntityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan(packagesToScan());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        localContainerEntityManagerFactoryBean.setJpaProperties(additionalProperties());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(EntityManagerFactory entityManagerFactory) {
        return new TransactionTemplate(transactionManager(entityManagerFactory));
    }

    protected Properties additionalProperties() {
        Properties properties = new Properties();
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        properties.setProperty("hibernate.dialect", hibernateDialect);
        
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
              
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        properties.setProperty("hibernate.show_sql", "hibernate.show_sql");   
 
        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        //properties.setProperty("hibernate.format_sql", "hibernate.format_sql");
        
        return properties;
    }

    protected String[] packagesToScan() {
        return new String[]{
            "sh.model"
        };
    }
}

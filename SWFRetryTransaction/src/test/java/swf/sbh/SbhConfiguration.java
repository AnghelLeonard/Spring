package swf.sbh;

import com.amazonaws.services.simpleworkflow.flow.spring.WorkflowScope;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import net.ttddyy.dsproxy.listener.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import swf.sbh.activities.FindActivityClient;
import swf.sbh.activities.FindActivityClientImpl;

/**
 *
 * @author Anghel Leonard
 */
@Configuration
@ComponentScan(basePackages = {"swf.sbh"})
@EnableAspectJAutoProxy(proxyTargetClass = false)
@EnableTransactionManagement
public class SbhConfiguration {

    public static final String DATA_SOURCE_PROXY_NAME = "DSProxy";
    
    @Bean
    protected static CustomScopeConfigurer createScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope("workflow", new WorkflowScope());
        return customScopeConfigurer;
    }

    @Bean
    @Scope("workflow")
    protected FindActivityClient getFindActivityClient() {
        return new FindActivityClientImpl();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "close")
    public javax.sql.DataSource actualDataSource() {
        Properties driverProperties = new Properties();
        driverProperties.setProperty("url", "jdbc:mysql://localhost:3306/items?createDatabaseIfNotExist=true");
        driverProperties.setProperty("user", "root");
        driverProperties.setProperty("password", "java797b");

        Properties properties = new Properties();
        properties.put("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        properties.put("dataSourceProperties", driverProperties);
        properties.setProperty("maximumPoolSize", String.valueOf(10));        
        return new HikariDataSource(new HikariConfig(properties));
    }

    @Primary
    @Bean
    @DependsOn("actualDataSource")
    public javax.sql.DataSource dataSource() {
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
    @DependsOn("dataSource")
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
    
    protected Properties additionalProperties() {
        Properties properties = new Properties();
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        properties.setProperty("hibernate.show_sql", "true");

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        properties.setProperty("hibernate.format_sql", "true");

        // Turn on statistics
        properties.setProperty("hibernate.generate_statistics", "true");

        return properties;
    }

    protected String[] packagesToScan() {
        return new String[]{
            "swf.sbh.model"
        };
    }
}

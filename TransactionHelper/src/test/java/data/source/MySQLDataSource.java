package data.source;

import data.utils.Util;
import data.utils.IdentifiersEnum;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import net.ttddyy.dsproxy.listener.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.vendor.Database;
import data.test.samples.JPASimpleTransactionTest;

/**
 *
 * @author Anghel Leonard
 */
public class MySQLDataSource implements GenericDataSource {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JPASimpleTransactionTest.class.getName());

    private static final String HIKARICP_USAGE_AND_POOL_SIZE
            = "Your MySQL data source will %s";
    
    public static final String DATA_SOURCE_PROXY_NAME = "MySQLdsProxyTest";
    public static Properties PROPERTIES = new Properties();

    public MySQLDataSource() {
        PROPERTIES = Util.readAllProperties("jdbc-mysql.properties");
    }

    @Override
    public DataSource actualDataSource(boolean hikaricp, int poolsize) {
        
        LOG.info(String.format(HIKARICP_USAGE_AND_POOL_SIZE, (hikaricp
                ? "use HikariCP connection pool with a size of " + (poolsize == 0
                        ? 3 : poolsize) : "not use HikariCP connection pool")));

        String jdbcUrlFinal = PROPERTIES.getProperty("jdbc.url").
                concat("?createDatabaseIfNotExist=".
                        concat(PROPERTIES.getProperty("createDatabaseIfNotExist"))).
                concat("&rewriteBatchedStatements=".
                        concat(PROPERTIES.getProperty("rewriteBatchedStatements")).
                        concat("&cachePrepStmts=".
                                concat(PROPERTIES.getProperty("cachePrepStmts"))).
                        concat("&useServerPrepStmts=".
                                concat(PROPERTIES.getProperty("useServerPrepStmts"))));

        if (!hikaricp) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl(jdbcUrlFinal + "&user=" + PROPERTIES.getProperty("jdbc.username") + "&password=" + PROPERTIES.getProperty("jdbc.password"));

            return dataSource;
        } else {

            Properties driverProperties = new Properties();
            driverProperties.setProperty("url", jdbcUrlFinal);
            driverProperties.setProperty("user", PROPERTIES.getProperty("jdbc.username"));
            driverProperties.setProperty("password", PROPERTIES.getProperty("jdbc.password"));

            Properties hikariProperties = new Properties();
            hikariProperties.put("dataSourceClassName", PROPERTIES.getProperty("jdbc.dataSourceClassName"));
            hikariProperties.put("dataSourceProperties", driverProperties);
            hikariProperties.setProperty("maximumPoolSize", String.valueOf(poolsize == 0 ? 3 : poolsize));
            return new HikariDataSource(new HikariConfig(hikariProperties));
        }
    }

    @Override
    public DataSource proxyDataSource(boolean hikaricp, int poolsize) {
        SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
        loggingListener.setQueryLogEntryCreator(new DefaultQueryLogEntryCreator());
        return ProxyDataSourceBuilder
                .create(actualDataSource(hikaricp, poolsize))
                .name(DATA_SOURCE_PROXY_NAME)
                .listener(loggingListener)
                .countQuery()
                .build();
    }

    @Override
    public Class<? extends DataSource> dataSourceClassName() {
        return MysqlDataSource.class;
    }

    @Override
    public String hibernateDialect() {
        return PROPERTIES.getProperty("hibernate.dialect");
    }

    @Override
    public List<IdentifiersEnum.IdentifierStrategy> supportedIdentifiers() {
        return Arrays.asList(IdentifiersEnum.IdentifierStrategy.IDENTITY);
    }

    @Override
    public Database database() {
        return Database.MYSQL;
    }

    @Override
    public String toString() {
        return "MySQLDataSource{" + "dataSourceClassName=" + PROPERTIES.getProperty("jdbc.dataSourceClassName")
                + ", jdbcUrl=" + PROPERTIES.getProperty("jdbc.url") + ", jdbcUser=" + PROPERTIES.getProperty("jdbc.username") + ", jdbcPassword="
                + PROPERTIES.getProperty("jdbc.password") + ", hibernateDialect=" + PROPERTIES.getProperty("hibernate.dialect") + ", createDatabaseIfNotExist="
                + PROPERTIES.getProperty("createDatabaseIfNotExist") + ", rewriteBatchedStatements=" + PROPERTIES.getProperty("rewriteBatchedStatements")
                + ", cachePrepStmts=" + PROPERTIES.getProperty("cachePrepStmts") + ", useServerPrepStmts=" + PROPERTIES.getProperty("useServerPrepStmts") + '}';
    }  
}

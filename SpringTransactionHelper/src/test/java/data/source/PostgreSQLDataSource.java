package data.source;

import data.utils.Util;
import data.utils.IdentifiersEnum;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import net.ttddyy.dsproxy.listener.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.vendor.Database;
import data.test.samples.JPASimpleTransactionTest;

/**
 *
 * @author Anghel Leonard
 */
public class PostgreSQLDataSource implements GenericDataSource {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JPASimpleTransactionTest.class.getName());

    private static final String HIKARICP_USAGE_AND_POOL_SIZE
            = "Your PostgreSQL data source will %s";

    public static final String DATA_SOURCE_PROXY_NAME = "PostgreSQLdsProxyTest";
    public static Properties PROPERTIES = new Properties();

    public PostgreSQLDataSource() {
        PROPERTIES = Util.readAllProperties("jdbc-postgresql.properties");
    }

    @Override
    public DataSource actualDataSource(boolean hikaricp, int poolsize) {

        LOG.info(String.format(HIKARICP_USAGE_AND_POOL_SIZE, (hikaricp
                ? "use HikariCP connection pool with a size of " + (poolsize == 0
                        ? 3 : poolsize) : "not use HikariCP connection pool")));

        if (!hikaricp) {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setDatabaseName(PROPERTIES.getProperty("jdbc.databaseName"));
            dataSource.setServerName(PROPERTIES.getProperty("jdbc.serverName"));
            dataSource.setPortNumber(Integer.parseInt(PROPERTIES.getProperty("jdbc.portNumber")));
            dataSource.setUser(PROPERTIES.getProperty("jdbc.user"));
            dataSource.setPassword(PROPERTIES.getProperty("jdbc.password"));

            return dataSource;
        } else {

            Properties driverProperties = new Properties();
            driverProperties.setProperty("databaseName", PROPERTIES.getProperty("jdbc.databaseName"));
            driverProperties.setProperty("serverName", PROPERTIES.getProperty("jdbc.serverName"));
            driverProperties.setProperty("user", PROPERTIES.getProperty("jdbc.user"));
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
        return PGSimpleDataSource.class;
    }

    @Override
    public String hibernateDialect() {
        return PROPERTIES.getProperty("hibernate.dialect");
    }

    @Override
    public List<IdentifiersEnum.IdentifierStrategy> supportedIdentifiers() {
        return Arrays.asList(IdentifiersEnum.IdentifierStrategy.SEQUENCE);
    }

    @Override
    public Database database() {
        return Database.POSTGRESQL;
    }

    @Override
    public String toString() {
        return "PostgreSQLDataSource{" + "dataSourceClassName=" + PROPERTIES.getProperty("jdbc.dataSourceClassName")
                + ", databaseName=" + PROPERTIES.getProperty("jdbc.databaseName") + ", serverName=" + PROPERTIES.getProperty("jdbc.serverName")
                + ", jdbcUser=" + PROPERTIES.getProperty("jdbc.username") + ", jdbcPassword="
                + PROPERTIES.getProperty("jdbc.password") + ", hibernateDialect=" + PROPERTIES.getProperty("hibernate.dialect") + '}';
    }
}

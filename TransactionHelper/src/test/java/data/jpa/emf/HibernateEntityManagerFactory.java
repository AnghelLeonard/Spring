package data.jpa.emf;

import data.utils.PersistenceUnitInfoImpl;
import data.source.GenericDataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.MappedSuperclass;
import javax.persistence.spi.PersistenceUnitInfo;
import org.hibernate.Interceptor;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anghel Leonard
 */
public abstract class HibernateEntityManagerFactory {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HibernateEntityManagerFactory.class.getName());

    private static final String ENTITY_MANAGER_FACTORY
            = "Preparing the EntityManagerFactory for a %s";
    private static final String DATA_SOURCE_DETAILS
            = "Data source details: %s";

    private static EntityManagerFactory entityManagerFactory;

    private HibernateEntityManagerFactory() {
        // NOPE
    }

    public static EntityManagerFactory getEntityManagerFactory(GenericDataSource ds, boolean proxy, boolean hikaricp, int poolsize, Interceptor interceptor) {

        LOG.info(String.format(ENTITY_MANAGER_FACTORY, (proxy ? "proxied (ttddyy/datasource-proxy)" : "non-proxied") + " " + ds.database().name() + " database ..."));
        LOG.info(String.format(DATA_SOURCE_DETAILS, ds.toString()));

        Properties properties = new Properties();

        properties.put("hibernate.dialect", ds.hibernateDialect());
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.put("hibernate.connection.datasource", proxy ? ds.proxyDataSource(hikaricp, poolsize) : ds.actualDataSource(hikaricp, poolsize));

        PersistenceUnitInfo persistenceUnitInfo = new PersistenceUnitInfoImpl(
                ds.getClass().getSimpleName(), getClassesAnnotatedWithEntity(), properties);

        Map<String, Object> configuration = new HashMap<>();
        configuration.put(org.hibernate.jpa.AvailableSettings.INTERCEPTOR, interceptor);
        EntityManagerFactoryBuilderImpl entityManagerFactoryBuilder = new EntityManagerFactoryBuilderImpl(
                new PersistenceUnitInfoDescriptor(persistenceUnitInfo), configuration
        );
        entityManagerFactory = entityManagerFactoryBuilder.build();

        return entityManagerFactory;
    }

    private static List<String> getClassesAnnotatedWithEntity() {

        List<String> entities = new ArrayList<>();

        Reflections reflections = new Reflections("", new TypeAnnotationsScanner());
        Set<String> entityClasses = reflections.getStore().getTypesAnnotatedWith(Entity.class.getName());
        Set<String> mappedSuperClasses = reflections.getStore().getTypesAnnotatedWith(MappedSuperclass.class.getName());

        mappedSuperClasses.stream().forEach((clzz) -> {

            entities.add(clzz + ".class");

        });

        entityClasses.stream().forEach((clzz) -> {

            entities.add(clzz);

        });

        LOG.info("Entities found: " + entities);

        return entities;
    }
}

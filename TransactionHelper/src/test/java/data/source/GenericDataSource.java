package data.source;

import data.utils.IdentifiersEnum;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.orm.jpa.vendor.Database;

/**
 *
 * @author Anghel Leonard
 */
public interface GenericDataSource {    
    
    DataSource actualDataSource(boolean hikaricp, int poolsize);

    DataSource proxyDataSource(boolean hikaricp, int poolsize);

    Class<? extends DataSource> dataSourceClassName();

    String hibernateDialect();

    List<IdentifiersEnum.IdentifierStrategy> supportedIdentifiers();

    Database database();        
}

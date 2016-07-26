package sh.dao;

import java.io.Serializable;

/**
 *
 * @author Anghel Leonard
 */
public interface GenericDAO<T, ID extends Serializable> {

    T persist(T entity);
}

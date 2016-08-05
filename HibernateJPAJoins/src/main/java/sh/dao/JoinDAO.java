package sh.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Anghel Leonard
 */
public interface JoinDAO<T, ID extends Serializable> {

    // inner join
    public List<T> omInnerJoinAllJPQL();

    public List<T> moInnerJoinAllJPQL();

    public List<T> omInnerJoinAllNative();

    public List<T> moInnerJoinAllNative();

    public List<T> omInnerJoinWhereJPQL();

    public List<T> moInnerJoinWhereJPQL();

    public List<T> omInnerJoinWhereNative();

    public List<T> moInnerJoinWhereNative();

    // left join
    public List<T> omLeftJoinAllJPQL();

    public List<T> omLeftJoinAllNative();

    public List<T> moLeftJoinAllJPQL();

    public List<T> moLeftJoinAllNative();

    // right join
    public List<T> omRightJoinAllJPQL();

    public List<T> omRightJoinAllNative();

    public List<T> moRightJoinAllJPQL();

    public List<T> moRightJoinAllNative();

    // full join
    public List<T> omFullJoinAllJPQL();

    public List<T> omFullJoinAllNative();

    public List<T> moFullJoinAllJPQL();

    public List<T> moFullJoinAllNative();

    // left excluding join
    public List<T> omLeftExcludingJoinAllJPQL();

    public List<T> omLeftExcludingJoinAllNative();

    public List<T> moLeftExcludingJoinAllJPQL();

    public List<T> moLeftExcludingJoinAllNative();

    // right excluding join
    public List<T> omRightExcludingJoinAllJPQL();

    public List<T> omRightExcludingJoinAllNative();

    public List<T> moRightExcludingJoinAllJPQL();

    public List<T> moRightExcludingJoinAllNative();

    // outer excluding join
    public List<T> omOuterExcludingJoinAllJPQL();

    public List<T> omOuterExcludingJoinAllNative();
    
    public List<T> moOuterExcludingJoinAllJPQL();

    public List<T> moOuterExcludingJoinAllNative();
}

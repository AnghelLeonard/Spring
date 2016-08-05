package sh.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anghel Leonard
 */
@Repository
@Transactional
public class JoinDAOImpl<T, ID extends Serializable> implements JoinDAO<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    ///////////////////////
    ///// INNER JOINs /////
    ///////////////////////
    @Override
    public List<T> omInnerJoinAllJPQL() {
        // Query the players from all tournaments
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Tournament b INNER JOIN b.players a");

        return query.getResultList();
    }

    @Override
    public List<T> omInnerJoinAllNative() { // PostgreSQL
        // Query the players from all tournaments
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM players a INNER JOIN tournaments b ON b.id = a.tournament_id");

        return query.getResultList();
    }

    @Override
    public List<T> moInnerJoinAllJPQL() {
        // Query the tournaments of all players
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Player a JOIN a.tournament b");

        return query.getResultList();
    }

    @Override
    public List<T> moInnerJoinAllNative() { // PostgreSQL
        // Query the tournaments of all players
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM tournaments b INNER JOIN players a ON b.id = a.tournament_id");

        return query.getResultList();

    }

    @Override
    public List<T> omInnerJoinWhereJPQL() {
        // Query all players that participate to Wimbledon tournament and have their rank smaller or equal to 5
        Query query = entityManager.
                createQuery("SELECT a.playername, a.rank FROM Tournament b INNER JOIN b.players a WHERE b.tournamentname = :tournament AND a.rank <= :rank");
        query.setParameter("tournament", "Wimbledon");
        query.setParameter("rank", 5);

        return query.getResultList();
    }

    @Override
    public List<T> omInnerJoinWhereNative() { // PostgreSQL
        // Query all players that participate to Wimbledon tournament and have their rank smaller or equal to 5
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, a.player_rank FROM players a INNER JOIN tournaments b ON b.id=a.tournament_id WHERE b.tournament_name = :tournament AND a.player_rank <= :rank");
        query.setParameter("tournament", "Wimbledon");
        query.setParameter("rank", 5);

        return query.getResultList();
    }

    @Override
    public List<T> moInnerJoinWhereJPQL() {
        // Query all tournaments that have players with rank smaller or equal to 5
        Query query = entityManager.
                createQuery("SELECT b.id, b.tournamentname FROM Player a JOIN a.tournament b WHERE a.rank <= :rank");
        query.setParameter("rank", 5);

        return query.getResultList();
    }

    @Override
    public List<T> moInnerJoinWhereNative() { // PostgreSQL
        // Query all tournaments that have players with rank smaller or equal to 5
        Query query = entityManager.
                createNativeQuery("SELECT b.id, b.tournament_name FROM tournaments b INNER JOIN players a ON b.id = a.tournament_id WHERE a.player_rank <= :rank");
        query.setParameter("rank", 5);

        return query.getResultList();
    }

    //////////////////////
    ///// LEFT JOINs /////
    //////////////////////
    @Override
    public List<T> omLeftJoinAllJPQL() {
        // Query all players even if they are not in tournaments
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Player a LEFT JOIN a.tournament b");

        return query.getResultList();
    }

    @Override
    public List<T> omLeftJoinAllNative() { // PostgreSQL
        // Query all players even if they are not in tournaments
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM players a LEFT JOIN tournaments b ON b.id = a.tournament_id");

        return query.getResultList();
    }

    @Override
    public List<T> moLeftJoinAllJPQL() {
        // Query all tournaments even if they don't have players
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Tournament b LEFT JOIN b.players a");

        return query.getResultList();
    }

    @Override
    public List<T> moLeftJoinAllNative() { // PostgreSQL
        // Query all tournaments even if they don't have players
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM tournaments b LEFT JOIN players a ON b.id = a.tournament_id");

        return query.getResultList();
    }

    ///////////////////////
    ///// RIGHT JOINs /////
    ///////////////////////
    @Override
    public List<T> omRightJoinAllJPQL() {
        // Query all tournaments even if they don't have players
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Player a RIGHT JOIN a.tournament b");

        return query.getResultList();
    }

    @Override
    public List<T> omRightJoinAllNative() { // PostgreSQL
        // Query all tournaments even if they don't have players
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM players a RIGHT JOIN tournaments b ON b.id = a.tournament_id");

        return query.getResultList();
    }

    @Override
    public List<T> moRightJoinAllJPQL() {
        // Query all players even if they are not in tournaments
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Tournament b RIGHT JOIN b.players a");

        return query.getResultList();
    }

    @Override
    public List<T> moRightJoinAllNative() { // PostgreSQL
        // Query all players even if they are not in tournaments
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM tournaments b RIGHT JOIN players a ON b.id = a.tournament_id");

        return query.getResultList();
    }

    //////////////////////
    ///// FULL JOINs /////
    //////////////////////
    @Override
    public List<T> omFullJoinAllJPQL() {
        // Query all players and tournaments
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Player a FULL JOIN a.tournament b");

        return query.getResultList();
    }

    @Override
    public List<T> omFullJoinAllNative() { // PostgreSQL
        // Query all players and tournaments
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM players a FULL JOIN tournaments b ON b.id = a.tournament_id");

        return query.getResultList();
    }

    @Override
    public List<T> moFullJoinAllJPQL() {
        // Query all tournaments and players
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Tournament b FULL JOIN b.players a");

        return query.getResultList();
    }

    @Override
    public List<T> moFullJoinAllNative() { // PostgreSQL
        // Query all tournaments and players
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM tournaments b FULL JOIN players a ON b.id = a.tournament_id");

        return query.getResultList();
    }

    ///////////////////////////////
    ///// LEFT EXLUDING JOINs /////
    ///////////////////////////////
    @Override
    public List<T> omLeftExcludingJoinAllJPQL() {
        // Query all players that are not in tournaments
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Player a LEFT JOIN a.tournament b WHERE b.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> omLeftExcludingJoinAllNative() {
        // Query all players that are not in tournaments
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM players a LEFT JOIN tournaments b ON b.id = a.tournament_id WHERE b.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> moLeftExcludingJoinAllJPQL() {
        // Query all tournaments that don't have players
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Tournament b LEFT JOIN b.players a WHERE a.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> moLeftExcludingJoinAllNative() {
        // Query all tournaments that don't have players
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM tournaments b LEFT JOIN players a ON b.id = a.tournament_id WHERE a.id IS NULL");

        return query.getResultList();
    }

    ////////////////////////////////
    ///// RIGHT EXLUDING JOINs /////
    ////////////////////////////////
    @Override
    public List<T> omRightExcludingJoinAllJPQL() {
        // Query all tournaments that don't have players
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Player a RIGHT JOIN a.tournament b WHERE a.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> omRightExcludingJoinAllNative() {
        // Query all tournaments that don't have players
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM players a RIGHT JOIN tournaments b ON b.id = a.tournament_id WHERE a.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> moRightExcludingJoinAllJPQL() {
        // Query all players that are not in tournaments
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Tournament b RIGHT JOIN b.players a WHERE b.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> moRightExcludingJoinAllNative() {
        // Query all players that are not in tournaments
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM tournaments b RIGHT JOIN players a ON b.id = a.tournament_id WHERE b.id IS NULL");

        return query.getResultList();
    }

    ////////////////////////////////
    ///// OUTER EXLUDING JOINs /////
    ////////////////////////////////
    @Override
    public List<T> omOuterExcludingJoinAllJPQL() {
        // Query all tournaments that don't have players and all players that don't participate in tournaments
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Player a FULL JOIN a.tournament b WHERE a.id IS NULL OR b.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> omOuterExcludingJoinAllNative() {
        // Query all tournaments that don't have players and all players that don't participate in tournaments
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM players a FULL JOIN tournaments b ON b.id = a.tournament_id WHERE a.id IS NULL OR b.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> moOuterExcludingJoinAllJPQL() {
        // Query all tournaments that don't have players and all players that don't participate in tournaments
        Query query = entityManager.
                createQuery("SELECT a.playername, b.tournamentname FROM Tournament b FULL JOIN b.players a WHERE a.id IS NULL OR b.id IS NULL");

        return query.getResultList();
    }

    @Override
    public List<T> moOuterExcludingJoinAllNative() {
        // Query all tournaments that don't have players and all players that don't participate in tournaments
        Query query = entityManager.
                createNativeQuery("SELECT a.player_name, b.tournament_name FROM tournaments b FULL JOIN players a ON b.id = a.tournament_id WHERE a.id IS NULL OR b.id IS NULL");

        return query.getResultList();
    }

}

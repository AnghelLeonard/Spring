package sh.service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sh.dao.JoinDAO;

/**
 *
 * @author Anghel Leonard
 */
@Repository
public class JoinServiceImpl implements JoinService {

    private static final Logger LOG = Logger.getLogger(JoinServiceImpl.class.getName());

    @Autowired
    private JoinDAO joinDAO;

    @Override
    @Transactional
    public void innerJoin() {

        LOG.info("--- INNER JOIN ---");

        List omijjpql = joinDAO.omInnerJoinAllJPQL();

        LOG.info("INNER JOIN (O->M | JPQL) - The players from all tournaments:");
        omijjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List omijnative = joinDAO.omInnerJoinAllNative();

        LOG.info("INNER JOIN (O->M | Native) - The players from all tournaments:");
        omijnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List moijjpql = joinDAO.moInnerJoinAllJPQL();

        LOG.info("INNER JOIN (M->O | JPQL) - The tournaments of all players:");
        moijjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List moijnative = joinDAO.moInnerJoinAllNative();

        LOG.info("INNER JOIN (M->O | Native) - The tournaments of all players:");
        moijnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List omijwjpql = joinDAO.omInnerJoinWhereJPQL();

        LOG.info("INNER JOIN (O->M | JPQL) - The players that participate to Wimbledon tournament and have their rank smaller or equal to 5:");
        omijwjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List omijwnative = joinDAO.omInnerJoinWhereNative();

        LOG.info("INNER JOIN (O->M | Native) - The players that participate to Wimbledon tournament and have their rank smaller or equal to 5:");
        omijwnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List moijwjpql = joinDAO.moInnerJoinWhereJPQL();

        LOG.info("INNER JOIN (M->O | JPQL) - All tournaments that have players with rank smaller or equal to 5:");
        moijwjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List moijwnative = joinDAO.moInnerJoinWhereNative();

        LOG.info("INNER JOIN (M->O | Native) -  All tournaments that have players with rank smaller or equal to 5:");
        moijwnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });
    }

    @Override
    @Transactional
    public void leftJoin() {
        LOG.info("--- LEFT JOIN ---");

        List omljjpql = joinDAO.omLeftJoinAllJPQL();

        LOG.info("LEFT JOIN (O->M | JPQL) - All players even if they are not in tournaments:");
        omljjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List omljnative = joinDAO.omLeftJoinAllNative();

        LOG.info("LEFT JOIN (O->M | Native) - All players even if they are not in tournaments:");
        omljnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List moljjpql = joinDAO.moLeftJoinAllJPQL();

        LOG.info("LEFT JOIN (M->O | JPQL) - All tournaments even if they don't have players:");
        moljjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List moljnative = joinDAO.moLeftJoinAllNative();

        LOG.info("LEFT JOIN (M->O | Native) - All tournaments even if they don't have players:");
        moljnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });
    }

    @Override
    @Transactional
    public void rightJoin() {
        LOG.info("--- RIGHT JOIN ---");

        List omrjjpql = joinDAO.omRightJoinAllJPQL();

        LOG.info("RIGHT JOIN (O->M | JPQL) -  All tournaments even if they don't have players:");
        omrjjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List omrjnative = joinDAO.omRightJoinAllNative();

        LOG.info("RIGHT JOIN (O->M | Native) - All tournaments even if they don't have players:");
        omrjnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List morjjpql = joinDAO.moRightJoinAllJPQL();

        LOG.info("RIGHT JOIN (M->O | JPQL) - All players even if they are not in tournaments:");
        morjjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List morjnative = joinDAO.moRightJoinAllNative();

        LOG.info("RIGHT JOIN (M->O | Native) - All players even if they are not in tournaments:");
        morjnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });
    }

    @Override
    @Transactional
    public void fullJoin() {

        LOG.info("--- FULL JOIN ---");

        List omfjjpql = joinDAO.omFullJoinAllJPQL();

        LOG.info("FULL JOIN (O->M | JPQL) -  All players and tournaments:");
        omfjjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List omfjnative = joinDAO.omFullJoinAllNative();

        LOG.info("FULL JOIN (O->M | Native) -  All players and tournaments:");
        omfjnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List mofjjpql = joinDAO.moFullJoinAllJPQL();

        LOG.info("FULL JOIN (M->O | JPQL) -  All tournaments and players:");
        mofjjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List mofjnative = joinDAO.moFullJoinAllNative();

        LOG.info("FULL JOIN (M->O | Native) -  All tournaments and players:");
        mofjnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

    }

    @Override
    @Transactional
    public void leftExcludingJoin() {

        List omlejjpql = joinDAO.omLeftExcludingJoinAllJPQL();

        LOG.info("LEFT EXCLUDING JOIN (O->M | JPQL) -  All players that are not in tournaments:");
        omlejjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List omlejnative = joinDAO.omLeftExcludingJoinAllNative();

        LOG.info("LEFT EXCLUDING JOIN (O->M | Native) -  All players that are not in tournaments:");
        omlejnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List molejjpql = joinDAO.moLeftExcludingJoinAllJPQL();

        LOG.info("LEFT EXCLUDING JOIN (M->O | JPQL) -  All tournaments that don't have players:");
        molejjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List molejnative = joinDAO.moLeftExcludingJoinAllNative();

        LOG.info("LEFT EXCLUDING JOIN (M->O | Native) -  All tournaments that don't have players:");
        molejnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });
    }

    @Override
    @Transactional
    public void rightExcludingJoin() {
        List omrejjpql = joinDAO.omRightExcludingJoinAllJPQL();

        LOG.info("RIGHT EXCLUDING JOIN (O->M | JPQL) -  All tournaments that don't have players:");
        omrejjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List omrejnative = joinDAO.omRightExcludingJoinAllNative();

        LOG.info("RIGHT EXCLUDING JOIN (O->M | Native) -  All tournaments that don't have players:");
        omrejnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List morejjpql = joinDAO.moRightExcludingJoinAllJPQL();

        LOG.info("RIGHT EXCLUDING JOIN (M->O | JPQL) - All players that are not in tournaments:");
        morejjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List morejnative = joinDAO.moRightExcludingJoinAllNative();

        LOG.info("RIGHT EXCLUDING JOIN (M->O | Native) -  All players that are not in tournaments:");
        morejnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });
    }

    @Override
    @Transactional
    public void outerExcludingJoin() {
        List omfejjpql = joinDAO.omOuterExcludingJoinAllJPQL();

        LOG.info("OUTER EXCLUDING JOIN (O->M | JPQL) - All tournaments that don't have players and all players that don't participate in tournaments:");
        omfejjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List omfejnative = joinDAO.omOuterExcludingJoinAllNative();

        LOG.info("OUTER EXCLUDING JOIN (O->M | Native) - All tournaments that don't have players and all players that don't participate in tournaments:");
        omfejnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        LOG.info("-----------------------------");

        List mofejjpql = joinDAO.moOuterExcludingJoinAllJPQL();

        LOG.info("FULL EXCLUDING JOIN (M->O | JPQL) - All tournaments that don't have players and all players that don't participate in tournaments:");
        mofejjpql.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });

        List mofejnative = joinDAO.moOuterExcludingJoinAllNative();

        LOG.info("FULL EXCLUDING JOIN (M->O | Native) - All tournaments that don't have players and all players that don't participate in tournaments:");
        mofejnative.stream().forEach((row) -> {
            LOG.log(Level.INFO, "Row: {0}", Arrays.toString((Object[]) row));
        });
    }
}

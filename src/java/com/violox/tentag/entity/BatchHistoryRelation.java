package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

@ApplicationScoped
public class BatchHistoryRelation implements Relation<BatchHistory, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> batch_key;
    @Inject
    private Relation<Batch, Integer> batch;

    @Override
    public BatchHistory post(BatchHistory item) {
        String sql = String.format("INSERT INTO `tentag`.`batch_history` "
                + "( `batch_id`"
                + ", `from_status`"
                + ", `to_status`"
                + ", `change_dt`) "
                + "VALUES (%d, %d, %d, '%4$tY-%4$tm-%4$te %4$tH:%4$tM:%4$tS'); ", item.getBatch().getId(), item.getFromStatus(), item.getToStatus(), item.getChangeDateTime()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                item.setSequence(rs.getInt(1));
            }
            return item;

        } catch (SQLException ex) {
            Logger.getLogger(BatchHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BatchHistory get(Key<Integer> key) {
        BatchHistory ret = new BatchHistory();
        String sql = String.format("SELECT `batch_history`.`sequence_id`"
                + ", `batch_history`.`batch_id`"
                + ", `batch_history`.`from_status`"
                + ", `batch_history`.`to_status`"
                + ", `batch_history`.`change_dt` "
                + "FROM `tentag`.`batch_history`"
                + "WHERE `batch_history`.`sequence_id` = %d;", key.getKey());

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ret.setSequence(rs.getInt("sequence_id"));
                batch_key.setKey(rs.getInt("batch_id"));
                ret.setBatch(batch.get(batch_key));
                ret.setFromStatus(rs.getInt("from_status"));
                ret.setToStatus(rs.getInt("to_status"));
                ret.setChangeDateTime(rs.getDate("change_dt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<BatchHistory> get() {
        ArrayList<BatchHistory> ret = new ArrayList<>();
        String sql = "SELECT `batch_history`.`sequence_id`"
                + ", `batch_history`.`batch_id`"
                + ", `batch_history`.`from_status`"
                + ", `batch_history`.`to_status`"
                + ", `batch_history`.`change_dt` "
                + "FROM `tentag`.`batch_history`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BatchHistory item = new BatchHistory();
                item.setSequence(rs.getInt("sequence_id"));
                batch_key.setKey(rs.getInt("batch_id"));
                item.setBatch(batch.get(batch_key));
                item.setFromStatus(rs.getInt("from_status"));
                item.setToStatus(rs.getInt("to_status"));
                item.setChangeDateTime(rs.getDate("change_dt"));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(BatchHistory item) {
        String sql = String.format("UPDATE `tentag`.`batch_history` "
                + "SET `batch_id` = %d"
                + ", `from_status` = %d"
                + ", `to_status` = %d"
                + ", `change_dt` = '%4$tY-%4$tm-%4$te %4$tH:%4$tM:%4$tS' "
                + "WHERE `sequence_id` = %d; ", item.getBatch().getId(), item.getFromStatus(), item.getToStatus(), item.getChangeDateTime(), item.getSequence()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(BatchHistory item) {
        String sql = String.format("DELETE FROM `tentag`.`batch_history` "
                + "WHERE `sequence_id` = %d; ", item.getSequence()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public void setKey(Integer key) {
        this.key = key;
    }

    public ArrayList<BatchHistory> getByBatch(Batch parent) {
        ArrayList<BatchHistory> ret = new ArrayList<>();
        String sql = String.format("SELECT `batch_history`.`sequence_id`"
                + ", `batch_history`.`batch_id`"
                + ", `batch_history`.`from_status`"
                + ", `batch_history`.`to_status`"
                + ", `batch_history`.`change_dt` "
                + "FROM `tentag`.`batch_history` "
                + "WHERE `batch_history`.`batch_id` = %d; ", parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BatchHistory item = new BatchHistory();
                item.setSequence(rs.getInt("sequence_id"));
                batch_key.setKey(rs.getInt("batch_id"));
                item.setBatch(batch.get(batch_key));
                item.setFromStatus(rs.getInt("from_status"));
                item.setToStatus(rs.getInt("to_status"));
                item.setChangeDateTime(rs.getDate("change_dt"));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

}

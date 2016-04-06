package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

@ApplicationScoped
public class PermitHistoryRelation implements Relation<PermitHistory, Integer> {

    private Integer key;
    
    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> permit_key;
    @Inject
    private Relation<Permit, Integer> permit;

    @Override
    public PermitHistory post(PermitHistory item) {
String sql = String.format("INSERT INTO `tentag`.`permit_history` "
        + "( `permit_id`"
        + ", `from_status`"
        + ", `to_status`"
        + ", `change_dt`) "
        + "VALUES (%d, %d, %d, '%4$tY-%4$tm-%4$te %4$tH:%4$tM:%4$tS'); "
        , item.getPermit().getId()
        , item.getFromStatus()
        , item.getToStatus()
        , item.getChangeDateTime()
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
            Logger.getLogger(PermitHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    }

    @Override
    public PermitHistory get(Key<Integer> key) {
        PermitHistory ret = new PermitHistory();
        String sql = String.format("SELECT `permit_history`.`sequence_id`"
                + ", `permit_history`.`permit_id`"
                + ", `permit_history`.`from_status`"
                + ", `permit_history`.`to_status`"
                + ", `permit_history`.`change_dt` "
                + "FROM `tentag`.`permit_history`"
                + "WHERE `permit_history`.`sequence_id` = %d;", key.getKey());

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                ret.setSequence(rs.getInt("sequence_id"));
                permit_key.setKey(rs.getInt("permit_id"));
                ret.setPermit(permit.get(permit_key));
                ret.setFromStatus(rs.getInt("from_status"));
                ret.setToStatus(rs.getInt("to_status"));
                ret.setChangeDateTime(rs.getDate("change_dt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<PermitHistory> get() {
        ArrayList<PermitHistory> ret = new ArrayList<>();
        String sql = "SELECT `permit_history`.`sequence_id`"
                + ", `permit_history`.`permit_id`"
                + ", `permit_history`.`from_status`"
                + ", `permit_history`.`to_status`"
                + ", `permit_history`.`change_dt` "
                + "FROM `tentag`.`permit_history`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PermitHistory item = new PermitHistory();
                item.setSequence(rs.getInt("sequence_id"));
                permit_key.setKey(rs.getInt("permit_id"));
                item.setPermit(permit.get(permit_key));
                item.setFromStatus(rs.getInt("from_status"));
                item.setToStatus(rs.getInt("to_status"));
                item.setChangeDateTime(rs.getDate("change_dt"));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(PermitHistory item) {
        String sql = String.format("UPDATE `tentag`.`permit_history` "
                + "SET `permit_id` = %d"
                + ", `from_status` = %d"
                + ", `to_status` = %d"
                + ", `change_dt` = '%4$tY-%4$tm-%4$te %4$tH:%4$tM:%4$tS' "
                + "WHERE `sequence_id` = %d; "
                , item.getPermit().getId()
                , item.getFromStatus()
                , item.getToStatus()
                , item.getChangeDateTime()
                , item.getSequence()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(PermitHistory item) {
        String sql = String.format("DELETE FROM `tentag`.`permit_history` "
                + "WHERE `sequence_id` = %d; ", item.getSequence()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
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

    ArrayList<PermitHistory> getByPermit(Permit parent) {
        ArrayList<PermitHistory> ret = new ArrayList<>();
        String sql = String.format("SELECT `permit_history`.`sequence_id`"
                + ", `permit_history`.`permit_id`"
                + ", `permit_history`.`from_status`"
                + ", `permit_history`.`to_status`"
                + ", `permit_history`.`change_dt` "
                + "FROM `tentag`.`permit_history`"
                + " WHERE `permit_history`.`permit_id` = %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PermitHistory item = new PermitHistory();
                item.setSequence(rs.getInt("sequence_id"));
                permit_key.setKey(rs.getInt("permit_id"));
                item.setPermit(permit.get(permit_key));
                item.setFromStatus(rs.getInt("from_status"));
                item.setToStatus(rs.getInt("to_status"));
                item.setChangeDateTime(rs.getDate("change_dt"));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitHistoryRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }
}

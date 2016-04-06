package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.*;

@ApplicationScoped
public class BatchStatusBitMapRelation implements Relation<BatchStatusBitMap, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public BatchStatusBitMap post(BatchStatusBitMap item) {
        String sql = String.format("INSERT INTO `tentag`.`batch_status_bit_map` "
                + "(`value`) "
                + "VALUES ('%s'); ", item.getValue()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                item.setBit(rs.getInt(1));
            }
            return item;

        } catch (SQLException ex) {
            Logger.getLogger(BatchStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BatchStatusBitMap get(Key<Integer> key) {
        BatchStatusBitMap ret = new BatchStatusBitMap();
        String sql = String.format("SELECT `batch_status_bit_map`.`bit`"
                + ", `batch_status_bit_map`.`value` "
                + "FROM `tentag`.`batch_status_bit_map`; "
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setBit(rs.getInt("bit"));
                ret.setValue(rs.getString("value"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<BatchStatusBitMap> get() {
        ArrayList<BatchStatusBitMap> ret = new ArrayList<>();
        String sql = "SELECT `batch_status_bit_map`.`bit`"
                + ", `batch_status_bit_map`.`value` "
                + "FROM `tentag`.`batch_status_bit_map`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BatchStatusBitMap item = new BatchStatusBitMap();
                item.setBit(rs.getInt("bit"));
                item.setValue(rs.getString("value"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(BatchStatusBitMap item) {
        String sql = String.format("UPDATE `tentag`.`batch_status_bit_map` "
                + "SET `value` = '%s' "
                + "WHERE `bit` = %d; ", item.getValue(), item.getBit()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(BatchStatusBitMap item) {
        String sql = String.format("DELETE FROM `tentag`.`batch_status_bit_map` "
                + "WHERE `bit` = %d; ", item.getBit()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
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

}

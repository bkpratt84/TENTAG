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
import javax.sql.DataSource;

@ApplicationScoped
public class PermitStatusBitMapRelation implements Relation<PermitStatusBitMap, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public PermitStatusBitMap post(PermitStatusBitMap item) {
        String sql = String.format("INSERT INTO `tentag`.`permit_status_bit_map` "
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
            Logger.getLogger(PermitStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public PermitStatusBitMap get(Key<Integer> key) {
        PermitStatusBitMap ret = new PermitStatusBitMap();
        String sql = String.format("SELECT `permit_status_bit_map`.`bit`"
                + ", `permit_status_bit_map`.`value` "
                + "FROM `tentag`.`permit_status_bit_map`; "
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setBit(rs.getInt("bit"));
                ret.setValue(rs.getString("value"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<PermitStatusBitMap> get() {
        ArrayList<PermitStatusBitMap> ret = new ArrayList<>();
        String sql = "SELECT `permit_status_bit_map`.`bit`"
                + ", `permit_status_bit_map`.`value` "
                + "FROM `tentag`.`permit_status_bit_map`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PermitStatusBitMap item = new PermitStatusBitMap();
                item.setBit(rs.getInt("bit"));
                item.setValue(rs.getString("value"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(PermitStatusBitMap item) {
        String sql = String.format("UPDATE `tentag`.`permit_status_bit_map` "
                + "SET `value` = '%s' "
                + "WHERE `bit` = %d; ", item.getValue(), item.getBit()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(PermitStatusBitMap item) {
        String sql = String.format("DELETE FROM `tentag`.`permit_status_bit_map` "
                + "WHERE `bit` = %d; ", item.getBit()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitStatusBitMapRelation.class.getName()).log(Level.SEVERE, null, ex);
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

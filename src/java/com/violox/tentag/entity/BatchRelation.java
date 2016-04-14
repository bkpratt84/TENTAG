package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.*;
import java.sql.*;

@ApplicationScoped
public class BatchRelation implements Relation<Batch, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public Batch post(Batch item) {
        String sql = String.format("INSERT INTO `tentag`.`batch` "
                + "( `property_id`"
                + ", `printer_id`"
                + ", `batch_status`) "
                + "VALUES (%d, %d, %d); ", item.getProperty().getId(), item.getPrinter().getId(), item.getStatus()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getInt(1));
            }
            return item;

        } catch (SQLException ex) {
            Logger.getLogger(BatchRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Batch get(Key<Integer> key) {
        Batch ret = new Batch();
        String sql = String.format("SELECT `batch`.`batch_id`"
                + ", `batch`.`property_id`"
                + ", `batch`.`printer_id`"
                + ", `batch`.`batch_status` "
                + "FROM `tentag`.`batch`"
                + "WHERE `batch`.`batch_id` = %d;", key.getKey()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("batch_id"));
                ret.setStatus(rs.getInt("batch_status"));
                ret.setPropertyId(rs.getInt("property_id"));
                ret.setPrinterId(rs.getInt("printer_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<Batch> get() {
        ArrayList<Batch> ret = new ArrayList<>();
        String sql = "SELECT `batch`.`batch_id`"
                + ", `batch`.`property_id`"
                + ", `batch`.`printer_id`"
                + ", `batch`.`batch_status` "
                + "FROM `tentag`.`batch`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Batch item = new Batch();
                item.setId(rs.getInt("batch_id"));
                item.setStatus(rs.getInt("batch_status"));
                item.setPropertyId(rs.getInt("property_id"));
                item.setPrinterId(rs.getInt("printer_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(Batch item) {
        String sql = String.format("UPDATE `tentag`.`batch` "
                + "SET `property_id` = %d"
                + ", `printer_id` = %d"
                + ", `batch_status` = %d "
                + "WHERE `batch_id` = %d; ", item.getProperty(), item.getPrinter(), item.getStatus(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Batch item) {
        String sql = String.format("DELETE FROM `tentag`.`batch` "
                + "WHERE `batch_id` = %d;", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BatchRelation.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Batch> getByProperty(Property parent) {
        ArrayList<Batch> ret = new ArrayList<>();
        String sql = String.format("SELECT `batch`.`batch_id`"
                + ", `batch`.`property_id`"
                + ", `batch`.`printer_id`"
                + ", `batch`.`batch_status` "
                + "FROM `tentag`.`batch`"
                + " WHERE `batch`.`property_id`= %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Batch item = new Batch();
                item.setId(rs.getInt("batch_id"));
                item.setStatus(rs.getInt("batch_status"));
                item.setPropertyId(rs.getInt("property_id"));
                item.setPrinterId(rs.getInt("printer_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    public ArrayList<Batch> getByPrinter(Printer parent) {
        ArrayList<Batch> ret = new ArrayList<>();
        String sql = String.format("SELECT `batch`.`batch_id`"
                + ", `batch`.`property_id`"
                + ", `batch`.`printer_id`"
                + ", `batch`.`batch_status` "
                + "FROM `tentag`.`batch`"
                + " WHERE `batch`.`printer_id`= %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Batch item = new Batch();
                item.setId(rs.getInt("batch_id"));
                item.setStatus(rs.getInt("batch_status"));
                item.setPropertyId(rs.getInt("property_id"));
                item.setPrinterId(rs.getInt("printer_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BatchRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret; 
    }

    @Override
    public Batch getByAlternateKey(Batch item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

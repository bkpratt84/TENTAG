package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.*;

@ApplicationScoped
public class GroupPrinterRelation implements Relation<GroupPrinter, IntegerPair> {

    private IntegerPair key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public GroupPrinter post(GroupPrinter item) {
        String sql = String.format("INSERT INTO `tentag`.`group_printer` "
                + "(`group_id`, `printer_id`) "
                + "VALUES (%d, %d); ", item.getGroupId(), item.getPrinterId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GroupPrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return item;
    }

    @Override
    public GroupPrinter get(Key<IntegerPair> key) {
        GroupPrinter ret = new GroupPrinter();
        String sql = String.format("SELECT `group_printer`.`group_id`"
                + ", `group_printer`.`printer_id` "
                + "FROM `tentag`.`group_printer`"
                + "WHERE `group_printer`.`group_id` = %d "
                + "and `group_printer`.`printer_id` = %d; ", key.getKey().getFirst(), key.getKey().getSecond()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setGroupId(rs.getInt("group_id"));
                ret.setPrinterId(rs.getInt("printer_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<GroupPrinter> get() {
        ArrayList<GroupPrinter> ret = new ArrayList<>();
        String sql = "SELECT `group_printer`.`group_id`, `group_printer`.`printer_id` "
                + "FROM `tentag`.`group_printer`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupPrinter item = new GroupPrinter();
                item.setGroupId(rs.getInt("group_id"));
                item.setPrinterId(rs.getInt("printer_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(GroupPrinter item) {
        String sql = String.format("UPDATE `tentag`.`group_printer` "
                + "SET `group_id` = %d "
                + ", `printer_id` = %d "
                + "WHERE `group_id` = %d AND `printer_id` = %d; ", item.getGroupId(), item.getPrinterId(), item.getGroupId(), item.getPrinterId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GroupPrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GroupPrinter item) {
        String sql = String.format("DELETE FROM `tentag`.`group_printer` "
                + "WHERE `group_id` = %d AND `printer_id` = %d;", item.getGroupId(), item.getPrinterId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GroupPrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public IntegerPair getKey() {
        return this.key;
    }

    @Override
    public void setKey(IntegerPair key) {
        this.key = key;
    }

    public ArrayList<GroupPrinter> getbyPrinter(Printer parent) {
        ArrayList<GroupPrinter> ret = new ArrayList<>();
        String sql = String.format("SELECT `group_printer`.`group_id`"
                + ", `group_printer`.`printer_id` "
                + "FROM `tentag`.`group_printer`"
                + " WHERE `group_printer`.`printer_id` = %d; ", parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupPrinter item = new GroupPrinter();
                item.setGroupId(rs.getInt("group_id"));
                item.setPrinterId(rs.getInt("printer_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    public ArrayList<GroupPrinter> getByGroup(Group parent) {
        ArrayList<GroupPrinter> ret = new ArrayList<>();
        String sql = String.format("SELECT `group_printer`.`group_id`"
                + ", `group_printer`.`printer_id` "
                + "FROM `tentag`.`group_printer`"
                + " WHERE `group_printer`.`group_id` = %d; ", parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupPrinter item = new GroupPrinter();
                item.setGroupId(rs.getInt("group_id"));
                item.setPrinterId(rs.getInt("printer_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public GroupPrinter getByAlternateKey(GroupPrinter item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

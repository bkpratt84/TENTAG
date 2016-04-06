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
public class GroupPrinterRelation implements Relation<GroupPrinter, IntegerPair> {

    private IntegerPair key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> group_key;
    @Inject
    private Relation<Group, Integer> group;

    @Inject
    private Key<Integer> printer_key;
    @Inject
    private Relation<Printer, Integer> printer;

    @Override
    public GroupPrinter post(GroupPrinter item) {
        String sql = String.format("INSERT INTO `tentag`.`group_printer` "
                + "(`group_id`, `printer_id`) "
                + "VALUES (%d, %d); ", item.getGroup().getId(), item.getPrinter().getId()
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
                group_key.setKey(rs.getInt("group_id"));
                ret.setGroup(group.get(group_key));
                printer_key.setKey(rs.getInt("printer_id"));
                ret.setPrinter(printer.get(printer_key));
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
                group_key.setKey(rs.getInt("group_id"));
                item.setGroup(group.get(group_key));
                printer_key.setKey(rs.getInt("printer_id"));
                item.setPrinter(printer.get(printer_key));
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
                + "WHERE `group_id` = %d AND `printer_id` = %d; "
                , item.getGroup().getId()
                , item.getPrinter().getId()
                , item.getGroup().getId()
                , item.getPrinter().getId()
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
                + "WHERE `group_id` = %d AND `printer_id` = %d;"
                , item.getGroup().getId()
                , item.getPrinter().getId()
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
                + " WHERE `group_printer`.`printer_id` = %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupPrinter item = new GroupPrinter();
                group_key.setKey(rs.getInt("group_id"));
                item.setGroup(group.get(group_key));
                printer_key.setKey(rs.getInt("printer_id"));
                item.setPrinter(printer.get(printer_key));
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
                + " WHERE `group_printer`.`group_id` = %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupPrinter item = new GroupPrinter();
                group_key.setKey(rs.getInt("group_id"));
                item.setGroup(group.get(group_key));
                printer_key.setKey(rs.getInt("printer_id"));
                item.setPrinter(printer.get(printer_key));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

}

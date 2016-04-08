package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

@ApplicationScoped
public class GroupPropertyRelation implements Relation<GroupProperty, IntegerPair> {

    private IntegerPair key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public GroupProperty post(GroupProperty item) {
        String sql = String.format("INSERT INTO `tentag`.`group_property` "
                + "(`group_id`, `property_id`) "
                + "VALUES (%d, %d); ", item.getGroupId(), item.getPropertyId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GroupPropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return item;
    }

    @Override
    public GroupProperty get(Key<IntegerPair> key) {
        GroupProperty ret = new GroupProperty();
        String sql = String.format("SELECT `group_property`.`group_id`"
                + ", `group_property`.`property_id` "
                + "FROM `tentag`.`group_property`"
                + "WHERE `group_property`.`group_id` = %d "
                + "and `group_property`.`property_id` = %d; ", key.getKey().getFirst(), key.getKey().getSecond()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setGroupId(rs.getInt("group_id"));
                ret.setPropertyId(rs.getInt("property_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<GroupProperty> get() {
        ArrayList<GroupProperty> ret = new ArrayList<>();
        String sql = "SELECT `group_property`.`group_id`, `group_property`.`property_id` "
                + "FROM `tentag`.`group_property`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupProperty item = new GroupProperty();
                item.setGroupId(rs.getInt("group_id"));
                item.setPropertyId(rs.getInt("property_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(GroupProperty item) {
        String sql = String.format("UPDATE `tentag`.`group_property` "
                + "SET `group_id` = %d "
                + ", `property_id` = %d "
                + "WHERE `group_id` = %d AND `property_id` = %d; ", item.getGroupId(), item.getPropertyId(), item.getGroupId(), item.getPropertyId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GroupPropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(GroupProperty item) {
        String sql = String.format("DELETE FROM `tentag`.`group_property` "
                + "WHERE `group_id` = %d AND `property_id` = %d;", item.getGroupId(), item.getPropertyId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GroupPropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<GroupProperty> getByProperty(Property parent) {
        ArrayList<GroupProperty> ret = new ArrayList<>();
        String sql = String.format("SELECT `group_property`.`group_id`"
                + ", `group_property`.`property_id` "
                + "FROM `tentag`.`group_property`"
                + " WHERE `group_property`.`property_id` = %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupProperty item = new GroupProperty();
                item.setGroupId(rs.getInt("group_id"));
                item.setPropertyId(rs.getInt("property_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    ArrayList<GroupProperty> getByGroup(Group parent) {
        ArrayList<GroupProperty> ret = new ArrayList<>();
        String sql = String.format("SELECT `group_property`.`group_id`"
                + ", `group_property`.`property_id` "
                + "FROM `tentag`.`group_property`"
                + " WHERE `group_property`.`group_id` = %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GroupProperty item = new GroupProperty();
                item.setGroupId(rs.getInt("group_id"));
                item.setPropertyId(rs.getInt("property_id"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupPropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public GroupProperty getByAlternateKey(GroupProperty item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

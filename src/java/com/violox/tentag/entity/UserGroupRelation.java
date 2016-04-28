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
public class UserGroupRelation implements Relation<UserGroup, IntegerPair> {

    private IntegerPair key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public UserGroup post(UserGroup item) {
        /*

         */
        String sql = String.format("INSERT INTO `tentag`.`user_group` "
                + "(`user_id`"
                + ", `group_id`"
                + ", `user_name`) "
                //+ ", `group_name`) " // this is actually the role name
                + "VALUES (%d, %d, '%s'); ", item.getUserId(), item.getGroupId(), item.getUserName()
                //+ "VALUES (%d, %d, '%s', '%s'); ", item.getUserId(), item.getGroupId(), item.getUserName(), item.getRoleName()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return item;

    }

    @Override
    public UserGroup get(Key<IntegerPair> key) {
        UserGroup ret = new UserGroup();
        String sql = String.format("SELECT `user_group`.`user_id`"
                + ", `user_group`.`group_id`"
                + ", `user_group`.`user_name`"
                //+ ", `user_group`.`group_name` "
                + "FROM `tentag`.`user_group` "
                + "WHERE `user_group`.`user_id` = %d "
                + "and `user_group`.`group_id` = %d; "
                , key.getKey().getFirst()
                , key.getKey().getSecond()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setUserId(rs.getInt("user_id"));
                ret.setGroupId(rs.getInt("group_id"));
                ret.setUserName(rs.getString("user_name"));
                //ret.setRoleName(rs.getString("group_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public ArrayList<UserGroup> get() {
        ArrayList<UserGroup> ret = new ArrayList<>();
        String sql = "SELECT `user_group`.`user_id`"
                + ", `user_group`.`group_id`"
                + ", `user_group`.`user_name`"
                //+ ", `user_group`.`group_name` "
                + "FROM `tentag`.`user_group`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserGroup item = new UserGroup ();
                item.setUserId(rs.getInt("user_id"));
                item.setGroupId(rs.getInt("group_id"));
                item.setUserName(rs.getString("user_name"));
                //item.setRoleName(rs.getString("group_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public void put(UserGroup item) {

        String sql = String.format("UPDATE `tentag`.`user_group` "
                + "SET `user_id` = %d"
                + ", `group_id` = %d"
                + ", `user_name` = '%s'"
                //+ ", `group_name` = '%s' "
                + "WHERE `user_id` = %d AND `group_id` = %d; "
                , item.getUserId()
                , item.getGroupId()
                , item.getUserName()
                //, item.getRoleName()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(UserGroup item) {

        String sql = String.format("DELETE FROM `tentag`.`user_group` "
                + "WHERE `user_id` = %d AND `group_id` = %d; "
                , item.getUserId()
                , item.getGroupId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public IntegerPair getKey() {
        return key;

    }

    @Override
    public void setKey(IntegerPair key) {

        this.key = key;
    }

    public ArrayList<UserGroup> getByUser(User parent) {
        ArrayList<UserGroup> ret = new ArrayList<>();
        String sql = String.format("SELECT `user_group`.`user_id`"
                + ", `user_group`.`group_id`"
                + ", `user_group`.`user_name`"
                //+ ", `user_group`.`group_name` "
                + "FROM `tentag`.`user_group` "
                + "WHERE `user_group`.`user_id` = %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserGroup item = new UserGroup ();
                item.setUserId(rs.getInt("user_id"));
                item.setGroupId(rs.getInt("group_id"));
                item.setUserName(rs.getString("user_name"));
                //item.setRoleName(rs.getString("group_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;  
    }

    public ArrayList<UserGroup> getByUserGroup(Group parent) {
        ArrayList<UserGroup> ret = new ArrayList<>();
        String sql = String.format("SELECT `user_group`.`user_id`"
                + ", `user_group`.`group_id`"
                + ", `user_group`.`user_name`"
                //+ ", `user_group`.`group_name` "
                + "FROM `tentag`.`user_group` "
                + "WHERE `user_group`.`group_id` = %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserGroup item = new UserGroup ();
                item.setUserId(rs.getInt("user_id"));
                item.setGroupId(rs.getInt("group_id"));
                item.setUserName(rs.getString("user_name"));
                //item.setRoleName(rs.getString("group_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;  
    }

    @Override
    public UserGroup getByAlternateKey(UserGroup item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

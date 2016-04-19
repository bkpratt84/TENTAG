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
public class UserRelation implements Relation<User, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public User post(User item) {
        String sql = String.format("INSERT INTO `tentag`.`user` (`user_name`, `password`, `role_name`) "
                + "VALUES ('%s', '%s', '%s'); ", item.getName(), item.getPassword(), item.getRole()
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
            Logger.getLogger(UserRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public User get(Key<Integer> key) {
        User ret = new User();
        String sql = String.format("SELECT `user`.`user_id`"
                + ", `user`.`user_name`"
                + ", `user`.`password` "
                + ", `user`.`role_name` "
                + "FROM `tentag`.`user`"
                + "WHERE `user`.`user_id` = %d; ", key.getKey()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("user_id"));
                ret.setName(rs.getString("user_name"));
                ret.setPassword(rs.getString("password"));
                ret.setRole(rs.getString("role_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<User> get() {
        ArrayList<User> ret = new ArrayList<>();
        String sql = "SELECT `user`.`user_id`, `user`.`user_name`, `user`.`password`, `user`.`role_name` "
                + "FROM `tentag`.`user`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User item = new User();
                item.setId(rs.getInt("user_id"));
                item.setName(rs.getString("user_name"));
                item.setPassword(rs.getString("password"));
                item.setRole(rs.getString("role_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(User item) {
        String sql = String.format("UPDATE `tentag`.`user` "
                + "SET `user_name` = '%s'"
                + ", `password` = '%s' "
                + ", `role_name` = '%s' "
                + "WHERE `user_id` = %d; ", item.getName(), item.getPassword(), item.getRole(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(User item) {
        String sql = String.format("DELETE FROM `tentag`.`user` "
                + "WHERE `user_id` = %d; ", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public void setKey(Integer key) {
        this.key = key;
    }

    @Override
    public User getByAlternateKey(User item) {
        User ret = new User();
        String sql = String.format("SELECT `user`.`user_id`"
                + ", `user`.`user_name`"
                + ", `user`.`password` "
                + ", `user`.`role_name` "
                + "FROM `tentag`.`user`"
                + "WHERE `user`.`user_name` = '%s'; ", item.getName()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("user_id"));
                ret.setName(rs.getString("user_name"));
                ret.setPassword(rs.getString("password"));
                ret.setRole(rs.getString("role_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }
}
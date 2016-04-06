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
public class UserRelation implements Relation<User, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> obj_key;
    @Inject
    private Relationship<UserGroup, User> groups;

    @Override
    public User post(User item) {
        String sql = String.format("INSERT INTO `tentag`.`user` (`user_name`, `password`) "
                + "VALUES ('%s', '%s'); ", item.getName(), item.getPassword()
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
                ArrayList<UserGroup> g = groups.getByParent(ret);
                ArrayList<Group> gr = new ArrayList<>();
                for (UserGroup n : g) {
                    gr.add(n.getGroup());
                }
                ret.setGroups(gr);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<User> get() {
        ArrayList<User> ret = new ArrayList<>();
        String sql = "SELECT `user`.`user_id`, `user`.`user_name`, `user`.`password` "
                + "FROM `tentag`.`user`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User item = new User();
                item.setId(rs.getInt("user_id"));
                item.setName(rs.getString("user_name"));
                item.setPassword(rs.getString("password"));
                ArrayList<UserGroup> g = groups.getByParent(item);
                ArrayList<Group> gr = new ArrayList<>();
                for (UserGroup n : g) {
                    gr.add(n.getGroup());
                }
                item.setGroups(gr);

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
                + "WHERE `user_id` = %d; ", item.getName(), item.getPassword(), item.getId()
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

}

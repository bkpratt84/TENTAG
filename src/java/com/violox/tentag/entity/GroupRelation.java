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
public class GroupRelation implements Relation<Group, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public Group post(Group item) {
        String sql = String.format("INSERT INTO `tentag`.`group` (`group_name`,`role_name`) "
                + "VALUES ('%s','%s'); "
                , item.getName()
                , item.getRole()
                //TODO set relations
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
            Logger.getLogger(GroupRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Group get(Key<Integer> key) {
         Group ret = new Group();
        String sql = String.format("SELECT `group`.`group_id`"
                + ", `group`.`group_name`"
                + ",  `group`.`role_name`"
                + "FROM `tentag`.`group` "
                + "WHERE `group`.`group_id` = %d; "
                , key.getKey()
	);

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("group_id"));
                ret.setName("group_name");
                ret.setRole(rs.getString("role_name"));
                //TODO Set Relationships
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public ArrayList<Group> get() {

        ArrayList<Group> ret = new ArrayList<>();
        String sql = "SELECT `group`.`group_id`"
                + ", `group`.`group_name` "
                + ", `group`.`role_name` "
                + "FROM `tentag`.`group`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Group item = new Group();
                item.setId(rs.getInt("group_id"));
                item.setName("group_name");
                item.setRole(rs.getString("role_name"));
                //TODO Set Relationships

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public void put(Group item) {

        String sql = String.format("UPDATE `tentag`.`group` "
                + "SET `group_name` = '%s' "
                + ", `role_name` = '%s' "
                + "WHERE `group_id` = %d; "
                , item.getName()
                , item.getRole()
                , item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Group item) {

        String sql = String.format("DELETE FROM `tentag`.`group` "
                + "WHERE `group_id` = %d; "
                , item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GroupRelation.class.getName()).log(Level.SEVERE, null, ex);
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

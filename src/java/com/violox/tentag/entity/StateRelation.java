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
public class StateRelation implements Relation<State, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public State post(State item) {
        String sql = String.format("INSERT INTO `tentag`.`state` "
                + "(`state_name`, `state_abbr`) "
                + "VALUES ('%s', '%s'); ", item.getName(), item.getAbbr()
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
            Logger.getLogger(StateRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public State get(Key<Integer> key) {
        State ret = new State();
        String sql = String.format("SELECT `state`.`state_id`"
                + ", `state`.`state_name`"
                + ", `state`.`state_abbr` "
                + "FROM `tentag`.`state`; ", key.getKey()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setAbbr(rs.getString("state_abbr"));
                ret.setName(rs.getString("state_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StateRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<State> get() {
        ArrayList<State> ret = new ArrayList<>();
        String sql = "SELECT `state`.`state_id`"
                + ", `state`.`state_name`"
                + ", `state`.`state_abbr` "
                + "FROM `tentag`.`state`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                State item = new State();
                item.setAbbr(rs.getString("state_abbr"));
                item.setName(rs.getString("state_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StateRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public void put(State item) {

        String sql = String.format("UPDATE `tentag`.`state` "
                + "SET `state_name` = '%s'"
                + ", `state_abbr` = '%s' "
                + "WHERE `state_id` = %d; ", item.getName(), item.getAbbr(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StateRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(State item) {

        String sql = String.format("DELETE FROM `tentag`.`state` "
                + "WHERE `state_id` = %d; ", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StateRelation.class.getName()).log(Level.SEVERE, null, ex);
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

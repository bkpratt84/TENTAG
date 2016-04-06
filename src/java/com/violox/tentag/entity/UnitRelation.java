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
public class UnitRelation implements Relation<Unit, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> property_key;
    @Inject
    private Relation<Property, Integer> property;

    @Override
    public Unit post(Unit item) {
        String sql = String.format("INSERT INTO `tentag`.`unit` (`property_id`"
                + ", `unit_name`"
                + ", `unit_notes`) "
                + "VALUES (%d, '%s', '%s'); ", item.getProperty().getId(), item.getName(), item.getNotes()
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
            Logger.getLogger(UnitRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Unit get(Key<Integer> key) {

        Unit ret = new Unit();
        String sql = String.format("SELECT `unit`.`unit_id`"
                + ", `unit`.`property_id`"
                + ", `unit`.`unit_name`"
                + ", `unit`.`unit_notes` "
                + "FROM `tentag`.`unit` "
                + "WHERE `unit`.`unit_id` = %d; ", key.getKey()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("unit_id"));
                property_key.setKey(rs.getInt("property_id"));
                ret.setProperty(property.get(property_key));
                ret.setName(rs.getString("unit_name"));
                ret.setNotes(rs.getString("unit_notes"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public ArrayList<Unit> get() {
        ArrayList<Unit> ret = new ArrayList<>();
        String sql = "SELECT `unit`.`unit_id`, `unit`.`property_id`, `unit`.`unit_name`, `unit`.`unit_notes` "
                + "FROM `tentag`.`unit`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Unit item = new Unit();
                item.setId(rs.getInt("unit_id"));
                property_key.setKey(rs.getInt("property_id"));
                item.setProperty(property.get(property_key));
                item.setName(rs.getString("unit_name"));
                item.setNotes(rs.getString("unit_notes"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public void put(Unit item) {
        String sql = String.format("UPDATE `tentag`.`unit` "
                + "SET `property_id` = %d"
                + ", `unit_name` = '%s'"
                + ", `unit_notes` = '%s' "
                + "WHERE `unit_id` = %d; ", item.getProperty().getId(), item.getName(), item.getNotes(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UnitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Unit item) {
        String sql = String.format("DELETE FROM `tentag`.`unit` "
                + "WHERE `unit_id` = %d; ",
                item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UnitRelation.class.getName()).log(Level.SEVERE, null, ex);
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

    ArrayList<Unit> getByProperty(Property parent) {
        ArrayList<Unit> ret = new ArrayList<>();
        String sql = String.format("SELECT `unit`.`unit_id`"
                + ", `unit`.`property_id`"
                + ", `unit`.`unit_name`"
                + ", `unit`.`unit_notes` "
                + "FROM `tentag`.`unit`"
                + " WHERE `unit`.`property_id` = %d; "
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Unit item = new Unit();
                item.setId(rs.getInt("unit_id"));
                property_key.setKey(rs.getInt("property_id"));
                item.setProperty(property.get(property_key));
                item.setName(rs.getString("unit_name"));
                item.setNotes(rs.getString("unit_notes"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

}

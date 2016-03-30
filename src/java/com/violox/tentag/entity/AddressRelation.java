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
public class AddressRelation implements Relation<Address, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> state_key;
    @Inject
    private Relation<State, Integer> state;

    @Override
    public Address post(Address item) {
        String sql = String.format("INSERT INTO `tentag`.`address`\n"
                + "(`address_line_1`,\n"
                + "`address_line_2`,\n"
                + "`address_city`,\n"
                + "`address_state_id`,\n"
                + "`address_zip`)\n"
                + "VALUES\n"
                + "('%s',\n"
                + "'%s',\n"
                + "'%s',\n"
                + "'%d',\n"
                + "'%s');", item.getAddressLine1(), item.getAddressLine2(), item.getCity(), item.getState().getId(), item.getZip()
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
            Logger.getLogger(AddressRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Address get(Key<Integer> key) {
        Address ret = new Address();
        String sql = String.format("SELECT `address`.`address_id`,\n"
                + "    `address`.`address_line_1`,\n"
                + "    `address`.`address_line_2`,\n"
                + "    `address`.`address_city`,\n"
                + "    `address`.`address_state_id`,\n"
                + "    `address`.`address_zip`\n"
                + "FROM `tentag`.`address`"
                + "WHERE `address`.`address_id` = %d;", key.getKey());

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("address_id"));
                ret.setAddressLine1(rs.getString("address_line_1"));
                ret.setAddressLine2(rs.getString("address_line_2"));
                ret.setCity(rs.getString("address_city"));
                ret.setZip(rs.getString("address_zip"));
                state_key.setKey(rs.getInt("address_state_id"));
                ret.setState(state.get(state_key));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<Address> get() {
        ArrayList<Address> ret = new ArrayList<>();
        String sql = "SELECT `address`.`address_id`,\n"
                + "    `address`.`address_line_1`,\n"
                + "    `address`.`address_line_2`,\n"
                + "    `address`.`address_city`,\n"
                + "    `address`.`address_state_id`,\n"
                + "    `address`.`address_zip`\n"
                + "FROM `tentag`.`address`;\n"
                + "SELECT * FROM tentag.address;";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Address item = new Address();
                item.setId(rs.getInt("address_id"));
                item.setAddressLine1(rs.getString("address_line_1"));
                item.setAddressLine2(rs.getString("address_line_2"));
                item.setCity(rs.getString("address_city"));
                item.setZip(rs.getString("address_zip"));
                state_key.setKey(rs.getInt("address_state_id"));
                item.setState(state.get(state_key));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(Address item) {
        String sql = String.format("UPDATE `tentag`.`address`\n"
                + "SET\n"
                + "`address_line_1` = '%s',\n"
                + "`address_line_2` = '%s',\n"
                + "`address_city` = '%s',\n"
                + "`address_state_id` = %d,\n"
                + "`address_zip` = '%s'\n"
                + "WHERE `address_id` = %d;", item.getAddressLine1(), item.getAddressLine2(), item.getCity(), item.getState().getId(), item.getZip(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AddressRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Address item) {
        String sql = String.format("DELETE FROM `tentag`.`address`\n"
                + "WHERE `address_id` = %d;", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AddressRelation.class.getName()).log(Level.SEVERE, null, ex);
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

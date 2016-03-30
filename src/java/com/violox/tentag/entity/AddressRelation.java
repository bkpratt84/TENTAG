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
public class AddressRelation implements Relation<Address, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public Address post(Address item) {
        // TODO implement here
        return null;
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<Address> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(Address item) {
        // TODO implement here
    }

    @Override
    public void delete(Address item) {
        // TODO implement here
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

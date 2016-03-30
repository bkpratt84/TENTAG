package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

@ApplicationScoped
public class AddressPrinterRelationship implements Relationship<Printer, Address> {

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> contact_key;
    @Inject
    private ContactRelation contact;

    @Inject
    private Key<Integer> state_key;
    @Inject
    private StateRelation state;

    @Override
    public ArrayList<Printer> getByParent(Address address) {

        ArrayList<Printer> ret = new ArrayList<>();
        String sql = String.format("SELECT `printer`.`printer_id`,\n"
                + "    `printer`.`printer_contact_id`,\n"
                + "    `printer`.`printer_address_id`,\n"
                + "    `printer`.`printer_name`,\n"
                + "    `printer`.`printer_is_active`\n"
                + "FROM `tentag`.`printer`"
                + "WHERE `printer`.`printer_address_id` = %d;", address.getId());
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Printer item = new Printer();
                item.setId(rs.getInt("printer_id"));
                item.setName(rs.getString("printer_name"));
                item.setIsActive(rs.getBoolean("printer_is_active"));
                item.setAddress(address);

                contact_key.setKey(rs.getInt("printer_contact_id"));
                item.setContact(contact.get(contact_key));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressPrinterRelationship.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public Address getByChild(Printer item) {
        Address ret = new Address();
        String sql = String.format("SELECT `address`.`address_id`,\n"
                + "    `address`.`address_line_1`,\n"
                + "    `address`.`address_line_2`,\n"
                + "    `address`.`address_city`,\n"
                + "    `address`.`address_state_id`,\n"
                + "    `address`.`address_zip`\n"
                + "FROM `tentag`.`address`"
                + "WHERE `address`.`address_id` = %d;", item.getAddress().getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ret.setId(rs.getInt("address_id"));
                ret.setAddressLine1(rs.getString("address_line_1"));
                ret.setAddressLine2(rs.getString("address_line_2"));
                ret.setCity(rs.getString("address_city"));
                ret.setZip(rs.getString("address_zip"));
                state_key.setKey(rs.getInt("address_state_id"));
                ret.setState(state.get(state_key));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddressPrinterRelationship.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}

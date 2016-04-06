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
public class PrinterRelation implements Relation<Printer, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> contact_key;
    @Inject
    private Relation<Contact, Integer> contact;

    @Inject
    private Key<Integer> address_key;
    @Inject
    private Relation<MailingAddress, Integer> address;

    @Override
    public Printer post(Printer item) {

        String sql = String.format("INSERT INTO `tentag`.`printer` "
                + "(`printer_contact_id`"
                + ", `printer_address_id`"
                + ", `printer_name`"
                + ", `printer_is_active`) "
                + "VALUES ('%s', '%s', '%s', '%s'); ", item.getContact().getId(), item.getAddress().getId(), item.getName(), item.getIsActive()
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
            Logger.getLogger(PrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Printer get(Key<Integer> key) {
        /*

         */
        Printer ret = new Printer();
        String sql = String.format("SELECT `printer`.`printer_id`"
                + ", `printer`.`printer_contact_id`"
                + ", `printer`.`printer_address_id`"
                + ", `printer`.`printer_name`"
                + ", `printer`.`printer_is_active` "
                + "FROM `tentag`.`printer` "
                + "WHERE `printer`.`printer_id` = %d; ", key.getKey()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("printer_id"));
                contact_key.setKey(rs.getInt("printer_contact_id"));
                ret.setContact(contact.get(contact_key));
                address_key.setKey(rs.getInt("printer_address_id"));
                ret.setAddress(address.get(address_key));
                ret.setName(rs.getString("printer_name"));
                ret.setIsActive(rs.getBoolean("printer_is_active"));
                //TODO set relations
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public ArrayList<Printer> get() {

        ArrayList<Printer> ret = new ArrayList<>();
        String sql = "SELECT `printer`.`printer_id`"
                + ", `printer`.`printer_contact_id`"
                + ", `printer`.`printer_address_id`"
                + ", `printer`.`printer_name`"
                + ", `printer`.`printer_is_active` "
                + "FROM `tentag`.`printer`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Printer item = new Printer();

                item.setId(rs.getInt("printer_id"));
                contact_key.setKey(rs.getInt("printer_contact_id"));
                item.setContact(contact.get(contact_key));
                address_key.setKey(rs.getInt("printer_address_id"));
                item.setAddress(address.get(address_key));
                item.setName(rs.getString("printer_name"));
                item.setIsActive(rs.getBoolean("printer_is_active"));
                //TODO set relations

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public void put(Printer item) {

        String sql = String.format("UPDATE `tentag`.`printer` "
                + "SET `printer_contact_id` = %d"
                + ", `printer_address_id` = %d"
                + ", `printer_name` = '%s'"
                + ", `printer_is_active` = %b "
                + "WHERE `printer_id` = %d; ", item.getContact().getId(), item.getAddress().getId(), item.getName(), item.getIsActive(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Printer item) {

        String sql = String.format("DELETE FROM `tentag`.`printer` "
                + "WHERE `printer_id` = %d; ", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Printer> getByAddress(MailingAddress parent) {
        ArrayList<Printer> ret = new ArrayList<>();
        String sql = String.format("SELECT `printer`.`printer_id`"
                + ", `printer`.`printer_contact_id`"
                + ", `printer`.`printer_address_id`"
                + ", `printer`.`printer_name`"
                + ", `printer`.`printer_is_active` "
                + "FROM `tentag`.`printer` "
                + "WHERE printer_address_id = %d; ", parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Printer item = new Printer();

                item.setId(rs.getInt("printer_id"));
                contact_key.setKey(rs.getInt("printer_contact_id"));
                item.setContact(contact.get(contact_key));
                address_key.setKey(rs.getInt("printer_address_id"));
                item.setAddress(address.get(address_key));
                item.setName(rs.getString("printer_name"));
                item.setIsActive(rs.getBoolean("printer_is_active"));
                //TODO set relations

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    ArrayList<Printer> getByContact(Contact parent) {
        ArrayList<Printer> ret = new ArrayList<>();
        String sql = String.format("SELECT `printer`.`printer_id`"
                + ", `printer`.`printer_contact_id`"
                + ", `printer`.`printer_address_id`"
                + ", `printer`.`printer_name`"
                + ", `printer`.`printer_is_active` "
                + "FROM `tentag`.`printer` "
                + "WHERE printer_contact_id = %d; ", parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Printer item = new Printer();

                item.setId(rs.getInt("printer_id"));
                contact_key.setKey(rs.getInt("printer_contact_id"));
                item.setContact(contact.get(contact_key));
                address_key.setKey(rs.getInt("printer_address_id"));
                item.setAddress(address.get(address_key));
                item.setName(rs.getString("printer_name"));
                item.setIsActive(rs.getBoolean("printer_is_active"));
                //TODO set relations

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrinterRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

}

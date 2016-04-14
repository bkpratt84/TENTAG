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
public class PropertyRelation implements Relation<Property, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public Property post(Property item) {

        String sql = String.format("INSERT INTO `tentag`.`property` "
                + "(`property_name`"
                + ", `property_contact_id`"
                + ", `property_address_id`"
                + ", `property_billing_address_id`) "
                + "VALUES ('%s', %d, %d, %d); ", item.getName(), item.getContact().getId(), item.getMailingAddress().getId(), item.getBillingAddress().getId()
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
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Property get(Key<Integer> key) {

        Property ret = new Property();
        String sql = String.format("SELECT `property`.`property_id`"
                + ", `property`.`property_name`"
                + ", `property`.`property_contact_id`"
                + ", `property`.`property_address_id`"
                + ", `property`.`property_billing_address_id` "
                + "FROM `tentag`.`property`"
                + "WHERE  `property`.`property_id` = %d; ", key.getKey()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setName(rs.getString("property_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<Property> get() {

        ArrayList<Property> ret = new ArrayList<>();
        String sql = "SELECT `property`.`property_id`"
                + ", `property`.`property_name`"
                + ", `property`.`property_contact_id`"
                + ", `property`.`property_address_id`"
                + ", `property`.`property_billing_address_id` "
                + "FROM `tentag`.`property`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Property item = new Property();
                item.setName(rs.getString("property_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public void put(Property item) {

        String sql = String.format("UPDATE `tentag`.`property` "
                + "SET `property_name` = '%s'"
                + ", `property_contact_id` = %d"
                + ", `property_address_id` = %d"
                + ", `property_billing_address_id` = %d "
                + "WHERE `property_id` = %d; ", item.getName(), item.getContact().getId(), item.getMailingAddress().getId(), item.getBillingAddress().getId(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Property item) {
        String sql = String.format("DELETE FROM `tentag`.`property` "
                + "WHERE `property_id` = %d; ", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
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

    /*
     * Foreign key refs
    */
    public ArrayList<Property> getByMailingAddress(MailingAddress address) {

        ArrayList<Property> ret = new ArrayList<>();
        String sql = String.format("SELECT `property`.`property_id`"
                + ", `property`.`property_name`"
                + ", `property`.`property_contact_id`"
                + ", `property`.`property_address_id`"
                + ", `property`.`property_billing_address_id` "
                + "FROM `tentag`.`property` "
                + "WHERE `property`.`property_address_id` = %d; ", address.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Property item = new Property();
                item.setName(rs.getString("property_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

        public ArrayList<Property> getByBillingAddress(Address parent) {

        ArrayList<Property> ret = new ArrayList<>();
        String sql = String.format("SELECT `property`.`property_id`"
                + ", `property`.`property_name`"
                + ", `property`.`property_contact_id`"
                + ", `property`.`property_address_id`"
                + ", `property`.`property_billing_address_id` "
                + "FROM `tentag`.`property` "
                + "WHERE `property`.`property_billing_address_id` = %d; ", parent.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Property item = new Property();
                item.setName(rs.getString("property_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    public ArrayList<Property> getByContact(Contact parent) {
        ArrayList<Property> ret = new ArrayList<>();
        String sql = String.format("SELECT `property`.`property_id`"
                + ", `property`.`property_name`"
                + ", `property`.`property_contact_id`"
                + ", `property`.`property_address_id`"
                + ", `property`.`property_billing_address_id` "
                + "FROM `tentag`.`property` "
                + "WHERE `property`.`property_contact_id` = %d; ", parent.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Property item = new Property();
                item.setName(rs.getString("property_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public Property getByAlternateKey(Property item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

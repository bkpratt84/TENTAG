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
public class PropertyRelation implements Relation<Property, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> contact_key;
    @Inject
    private Relation<Contact, Integer> contact;

    @Inject
    private Key<Integer> mailing_address_key;
    @Inject
    private Relation<MailingAddress, Integer> mailing_address;

    @Inject
    private Key<Integer> billing_address_key;
    @Inject
    private Relation<BillingAddress, Integer> billing_address;

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
                ret.setName(rs.getString(""));
                contact_key.setKey(rs.getInt(""));
                ret.setContact(contact.get(contact_key));
                mailing_address_key.setKey(rs.getInt(""));
                ret.setMailingAddress(mailing_address.get(mailing_address_key));
                billing_address_key.setKey(rs.getInt(""));
                ret.setBillingAddress(billing_address.get(billing_address_key));
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
                item.setName(rs.getString(""));
                contact_key.setKey(rs.getInt(""));
                item.setContact(contact.get(contact_key));
                mailing_address_key.setKey(rs.getInt(""));
                item.setMailingAddress(mailing_address.get(mailing_address_key));
                billing_address_key.setKey(rs.getInt(""));
                item.setBillingAddress(billing_address.get(billing_address_key));

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
                item.setName(rs.getString(""));
                contact_key.setKey(rs.getInt(""));
                item.setContact(contact.get(contact_key));
                mailing_address_key.setKey(rs.getInt(""));
                item.setMailingAddress(mailing_address.get(mailing_address_key));
                billing_address_key.setKey(rs.getInt(""));
                item.setBillingAddress(billing_address.get(billing_address_key));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

        public ArrayList<Property> getByBillingAddress(BillingAddress parent) {

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
                item.setName(rs.getString(""));
                contact_key.setKey(rs.getInt(""));
                item.setContact(contact.get(contact_key));
                mailing_address_key.setKey(rs.getInt(""));
                item.setMailingAddress(mailing_address.get(mailing_address_key));
                billing_address_key.setKey(rs.getInt(""));
                item.setBillingAddress(billing_address.get(billing_address_key));

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
                item.setName(rs.getString(""));
                contact_key.setKey(rs.getInt(""));
                item.setContact(contact.get(contact_key));
                mailing_address_key.setKey(rs.getInt(""));
                item.setMailingAddress(mailing_address.get(mailing_address_key));
                billing_address_key.setKey(rs.getInt(""));
                item.setBillingAddress(billing_address.get(billing_address_key));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

}

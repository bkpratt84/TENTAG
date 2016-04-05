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
public class ContactRelation implements Relation<Contact, Integer> {

    private Integer key;
    
    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public Contact post(Contact item) {
        String sql = String.format("INSERT INTO `tentag`.`contact`\n"
                + "(`contact_last`,\n"
                + "`contact_first`,\n"
                + "`contact_email`,\n"
                + "`contact_phone_p`,\n"
                + "`contact_phone_s`)\n"
                + "VALUES\n"
                + "('%s','%s','%s','%s','%s');", item.getLastName(), item.getFirstName(), item.getEmail(), item.getPrimaryPhone(), item.getSecondaryPhone()
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
            Logger.getLogger(ContactRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Contact get(Key<Integer> key) {
        Contact ret = new Contact();
        String sql = String.format("SELECT `contact`.`contact_id`,\n"
                + "    `contact`.`contact_last`,\n"
                + "    `contact`.`contact_first`,\n"
                + "    `contact`.`contact_email`,\n"
                + "    `contact`.`contact_phone_p`,\n"
                + "    `contact`.`contact_phone_s`\n"
                + "FROM `tentag`.`contact`\n"
                + "WHERE `contact`.`contact_id` = %d;", key.getKey());

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("contact_id"));
                ret.setLastName(rs.getString("contact_last"));
                ret.setFirstName(rs.getString("contact_first"));
                ret.setEmail(rs.getString("contact_email"));
                ret.setPrimaryPhone(rs.getString("contact_phone_p"));
                ret.setSecondaryPhone(rs.getString("contact_phone_s"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<Contact> get() {
        ArrayList<Contact> ret = new ArrayList<>();
        String sql = "SELECT `contact`.`contact_id`,\n"
                + "    `contact`.`contact_last`,\n"
                + "    `contact`.`contact_first`,\n"
                + "    `contact`.`contact_email`,\n"
                + "    `contact`.`contact_phone_p`,\n"
                + "    `contact`.`contact_phone_s`\n"
                + "FROM `tentag`.`contact`;";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contact item = new Contact();
                item.setId(rs.getInt("contact_id"));
                item.setLastName(rs.getString("contact_last"));
                item.setFirstName(rs.getString("contact_first"));
                item.setEmail(rs.getString("contact_email"));
                item.setPrimaryPhone(rs.getString("contact_phone_p"));
                item.setSecondaryPhone(rs.getString("contact_phone_s"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(Contact item) {
        String sql = String.format("UPDATE `tentag`.`contact`\n"
                + "SET\n"
                + "`contact_last` = '%s',\n"
                + "`contact_first` = '%s',\n"
                + "`contact_email` = '%s',\n"
                + "`contact_phone_p` = '%s',\n"
                + "`contact_phone_s` = '%s'\n"
                + "WHERE `contact_id` = %d;", item.getLastName(), item.getFirstName(), item.getEmail(), item.getPrimaryPhone(), item.getSecondaryPhone(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContactRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Contact item) {
        String sql = String.format("DELETE FROM `tentag`.`contact` "
                + "WHERE `contact_id` = %d;;", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContactRelation.class.getName()).log(Level.SEVERE, null, ex);
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

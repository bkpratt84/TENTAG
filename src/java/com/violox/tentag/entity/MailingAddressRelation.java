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
public class MailingAddressRelation implements Relation<MailingAddress, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> state_key;
    @Inject
    private Relation<State, Integer> state;

    @Override
    public MailingAddress post(MailingAddress item) {
        String sql = String.format("INSERT INTO `tentag`.`address`"
                + "(`address_line_1`, "
                + "`address_line_2`, "
                + "`address_city`, "
                + "`address_state_id`, "
                + "`address_zip`) "
                + "VALUES "
                + "('%s', "
                + "'%s', "
                + "'%s', "
                + "'%d', "
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
            Logger.getLogger(MailingAddressRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public MailingAddress get(Key<Integer> key) {
        MailingAddress ret = new MailingAddress();
        String sql = String.format("SELECT `address`.`address_id`,"
                + "    `address`.`address_line_1`,"
                + "    `address`.`address_line_2`,"
                + "    `address`.`address_city`,"
                + "    `address`.`address_state_id`,"
                + "    `address`.`address_zip`"
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
            Logger.getLogger(MailingAddressRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public ArrayList<MailingAddress> get() {
        ArrayList<MailingAddress> ret = new ArrayList<>();
        String sql = "SELECT `address`.`address_id`"
                + ",`address`.`address_line_1`"
                + ",`address`.`address_line_2`"
                + ",`address`.`address_city`"
                + ",`address`.`address_state_id`"
                + ",`address`.`address_zip` "
                + "FROM `tentag`.`address`;";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MailingAddress item = new MailingAddress();
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
            Logger.getLogger(MailingAddressRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    @Override
    public void put(MailingAddress item) {
        String sql = String.format("UPDATE `tentag`.`address` "
                + "SET `address_line_1` = '%s'"
                + ",`address_line_2` = '%s'"
                + ",`address_city` = '%s'"
                + ",`address_state_id` = %d"
                + ",`address_zip` = '%s' "
                + "WHERE `address_id` = %d;", item.getAddressLine1(), item.getAddressLine2(), item.getCity(), item.getState().getId(), item.getZip(), item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MailingAddressRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(MailingAddress item) {
        String sql = String.format("DELETE FROM `tentag`.`address` "
                + "WHERE `tentag`.`address` = %d;", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MailingAddressRelation.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<BillingAddress> getByStateBilling(State parent) {
        ArrayList<BillingAddress> ret = new ArrayList<>();
        String sql = String.format("SELECT `address`.`address_id`"
                + ", `address`.`address_line_1`"
                + ", `address`.`address_line_2`"
                + ", `address`.`address_city`"
                + ", `address`.`address_state_id`"
                + ", `address`.`address_zip` "
                + "FROM `tentag`.`address` "
                + "WHERE `address`.`address_state_id` = %d;"
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BillingAddress item = new BillingAddress();
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
            Logger.getLogger(MailingAddressRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }
    
        public ArrayList<MailingAddress> getByStateMailing(State parent) {
        ArrayList<MailingAddress> ret = new ArrayList<>();
        String sql = String.format("SELECT `address`.`address_id`"
                + ", `address`.`address_line_1`"
                + ", `address`.`address_line_2`"
                + ", `address`.`address_city`"
                + ", `address`.`address_state_id`"
                + ", `address`.`address_zip` "
                + "FROM `tentag`.`address` "
                + "WHERE `address`.`address_state_id` = %d;"
                , parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MailingAddress item = new MailingAddress();
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
            Logger.getLogger(MailingAddressRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

}

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
public class PermitRelation implements Relation<Permit, Integer> {

    private Integer key;

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Inject
    private Key<Integer> batch_key;
    @Inject
    private Relation<Batch, Integer> batch;

    @Inject
    private Key<Integer> unit_key;
    @Inject
    private Relation<Unit, Integer> unit;

    @Inject
    private Key<Integer> state_key;
    @Inject
    private Relation<State, Integer> state;

    @Override
    public Permit post(Permit item) {

        String sql = String.format("INSERT INTO `tentag`.`permit` "
                + "(`batch_id`"
                + ", `unit_id`"
                + ", `permit_status`"
                + ", `permit_is_open_parking`"
                + ", `permit_assignment`"
                + ", `permit_veh_make`"
                + ", `permit_veh_model`"
                + ", `permit_veh_color`"
                + ", `permit_veh_plate_number`"
                + ", `permit_veh_plate_state_id`) "
                + "VALUES (%d, %d, %d, %b, '%s', '%s', '%s', '%s', '%s', %d); ", item.getBatch().getId(), item.getUnit().getId(), item.getStatus(), item.getIsOpenParking(), item.getAssignment(), item.getVehicleMake(), item.getVehicleModel(), item.getVehicleColor(), item.getVehiclePlateNumber(), item.getVehiclePlateState().getId()
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
            Logger.getLogger(PermitRelation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Permit get(Key<Integer> key) {
        Permit ret = new Permit();
        String sql = String.format("SELECT `permit`.`permit_id`"
                + ", `permit`.`batch_id`"
                + ", `permit`.`unit_id`"
                + ", `permit`.`permit_status`"
                + ", `permit`.`permit_is_open_parking`"
                + ", `permit`.`permit_assignment`"
                + ", `permit`.`permit_veh_make`"
                + ", `permit`.`permit_veh_model`"
                + ", `permit`.`permit_veh_color`"
                + ", `permit`.`permit_veh_plate_number`"
                + ", `permit`.`permit_veh_plate_state_id` "
                + "FROM `tentag`.`permit` "
                + "WHERE `permit`.`permit_id` = %d; ", key.getKey()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ret.setId(rs.getInt("permit_id"));
                batch_key.setKey(rs.getInt("batch_id"));
                ret.setBatch(batch.get(batch_key));
                unit_key.setKey(rs.getInt("unit_id"));
                ret.setUnit(unit.get(unit_key));
                ret.setStatus(rs.getInt("permit_status"));
                ret.setIsOpenParking(rs.getBoolean("permit_is_open_parking"));
                ret.setAssignment(rs.getString("permit_assignment"));
                ret.setVehicleMake(rs.getString("permit_veh_make"));
                ret.setVehicleModel(rs.getString("permit_veh_model"));
                ret.setVehicleColor(rs.getString("permit_veh_color"));
                ret.setVehiclePlateNumber(rs.getString("permit_veh_plate_number"));
                state_key.setKey(rs.getInt("permit_veh_plate_state_id"));
                ret.setVehiclePlateState(state.get(state_key));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public ArrayList<Permit> get() {
        ArrayList<Permit> ret = new ArrayList<>();
        String sql = "SELECT `permit`.`permit_id`"
                + ", `permit`.`batch_id`"
                + ", `permit`.`unit_id`"
                + ", `permit`.`permit_status`"
                + ", `permit`.`permit_is_open_parking`"
                + ", `permit`.`permit_assignment`"
                + ", `permit`.`permit_veh_make`"
                + ", `permit`.`permit_veh_model`"
                + ", `permit`.`permit_veh_color`"
                + ", `permit`.`permit_veh_plate_number`"
                + ", `permit`.`permit_veh_plate_state_id` "
                + "FROM `tentag`.`permit`; ";
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Permit item = new Permit();

                item.setId(rs.getInt("permit_id"));
                batch_key.setKey(rs.getInt("batch_id"));
                item.setBatch(batch.get(batch_key));
                unit_key.setKey(rs.getInt("unit_id"));
                item.setUnit(unit.get(unit_key));
                item.setStatus(rs.getInt("permit_status"));
                item.setIsOpenParking(rs.getBoolean("permit_is_open_parking"));
                item.setAssignment(rs.getString("permit_assignment"));
                item.setVehicleMake(rs.getString("permit_veh_make"));
                item.setVehicleModel(rs.getString("permit_veh_model"));
                item.setVehicleColor(rs.getString("permit_veh_color"));
                item.setVehiclePlateNumber(rs.getString("permit_veh_plate_number"));
                state_key.setKey(rs.getInt("permit_veh_plate_state_id"));
                item.setVehiclePlateState(state.get(state_key));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

    @Override
    public void put(Permit item) {
        String sql = String.format("UPDATE `tentag`.`permit` "
                + "SET `batch_id` = %d"
                + ", `unit_id` = %d"
                + ", `permit_status` = %d"
                + ", `permit_is_open_parking` = %b"
                + ", `permit_assignment` = '%s'"
                + ", `permit_veh_make` = '%s'"
                + ", `permit_veh_model` = '%s'"
                + ", `permit_veh_color` = '%s'"
                + ", `permit_veh_plate_number` = '%s'"
                + ", `permit_veh_plate_state_id` = %d "
                + "WHERE `permit_id` = %d; ", item.getBatch().getId(), item.getUnit().getId(), item.getStatus(), item.getIsOpenParking(), item.getAssignment(), item.getVehicleMake(), item.getVehicleModel(), item.getVehicleColor(), item.getVehiclePlateNumber(), item.getVehiclePlateState().getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Permit item) {
        String sql = String.format("DELETE FROM `tentag`.`permit` "
                + "WHERE `permit_id` = %d; ", item.getId()
        );

        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermitRelation.class.getName()).log(Level.SEVERE, null, ex);
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

    ArrayList<Permit> getByUnit(Unit parent) {
        ArrayList<Permit> ret = new ArrayList<>();
        String sql = String.format("SELECT `permit`.`permit_id`"
                + ", `permit`.`batch_id`"
                + ", `permit`.`unit_id`"
                + ", `permit`.`permit_status`"
                + ", `permit`.`permit_is_open_parking`"
                + ", `permit`.`permit_assignment`"
                + ", `permit`.`permit_veh_make`"
                + ", `permit`.`permit_veh_model`"
                + ", `permit`.`permit_veh_color`"
                + ", `permit`.`permit_veh_plate_number`"
                + ", `permit`.`permit_veh_plate_state_id` "
                + "FROM `tentag`.`permit` "
                + "WHERE `permit`.`unit_id` = %d; ", parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Permit item = new Permit();

                item.setId(rs.getInt("permit_id"));
                batch_key.setKey(rs.getInt("batch_id"));
                item.setBatch(batch.get(batch_key));
                unit_key.setKey(rs.getInt("unit_id"));
                item.setUnit(unit.get(unit_key));
                item.setStatus(rs.getInt("permit_status"));
                item.setIsOpenParking(rs.getBoolean("permit_is_open_parking"));
                item.setAssignment(rs.getString("permit_assignment"));
                item.setVehicleMake(rs.getString("permit_veh_make"));
                item.setVehicleModel(rs.getString("permit_veh_model"));
                item.setVehicleColor(rs.getString("permit_veh_color"));
                item.setVehiclePlateNumber(rs.getString("permit_veh_plate_number"));
                state_key.setKey(rs.getInt("permit_veh_plate_state_id"));
                item.setVehiclePlateState(state.get(state_key));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    public ArrayList<Permit> getByState(State parent) {

        ArrayList<Permit> ret = new ArrayList<>();
        String sql = String.format("SELECT `permit`.`permit_id`"
                + ", `permit`.`batch_id`"
                + ", `permit`.`unit_id`"
                + ", `permit`.`permit_status`"
                + ", `permit`.`permit_is_open_parking`"
                + ", `permit`.`permit_assignment`"
                + ", `permit`.`permit_veh_make`"
                + ", `permit`.`permit_veh_model`"
                + ", `permit`.`permit_veh_color`"
                + ", `permit`.`permit_veh_plate_number`"
                + ", `permit`.`permit_veh_plate_state_id` "
                + "FROM `tentag`.`permit` "
                + "WHERE `permit`.`permit_veh_plate_state_id` = %d; ", parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Permit item = new Permit();

                item.setId(rs.getInt("permit_id"));
                batch_key.setKey(rs.getInt("batch_id"));
                item.setBatch(batch.get(batch_key));
                unit_key.setKey(rs.getInt("unit_id"));
                item.setUnit(unit.get(unit_key));
                item.setStatus(rs.getInt("permit_status"));
                item.setIsOpenParking(rs.getBoolean("permit_is_open_parking"));
                item.setAssignment(rs.getString("permit_assignment"));
                item.setVehicleMake(rs.getString("permit_veh_make"));
                item.setVehicleModel(rs.getString("permit_veh_model"));
                item.setVehicleColor(rs.getString("permit_veh_color"));
                item.setVehiclePlateNumber(rs.getString("permit_veh_plate_number"));
                state_key.setKey(rs.getInt("permit_veh_plate_state_id"));
                item.setVehiclePlateState(state.get(state_key));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

    ArrayList<Permit> getByBatch(Batch parent) {
        ArrayList<Permit> ret = new ArrayList<>();
        String sql = String.format("SELECT `permit`.`permit_id`"
                + ", `permit`.`batch_id`"
                + ", `permit`.`unit_id`"
                + ", `permit`.`permit_status`"
                + ", `permit`.`permit_is_open_parking`"
                + ", `permit`.`permit_assignment`"
                + ", `permit`.`permit_veh_make`"
                + ", `permit`.`permit_veh_model`"
                + ", `permit`.`permit_veh_color`"
                + ", `permit`.`permit_veh_plate_number`"
                + ", `permit`.`permit_veh_plate_state_id` "
                + "FROM `tentag`.`permit` "
                + "WHERE `permit`.`batch_id` = %d; ", parent.getId()
        );
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Permit item = new Permit();

                item.setId(rs.getInt("permit_id"));
                batch_key.setKey(rs.getInt("batch_id"));
                item.setBatch(batch.get(batch_key));
                unit_key.setKey(rs.getInt("unit_id"));
                item.setUnit(unit.get(unit_key));
                item.setStatus(rs.getInt("permit_status"));
                item.setIsOpenParking(rs.getBoolean("permit_is_open_parking"));
                item.setAssignment(rs.getString("permit_assignment"));
                item.setVehicleMake(rs.getString("permit_veh_make"));
                item.setVehicleModel(rs.getString("permit_veh_model"));
                item.setVehicleColor(rs.getString("permit_veh_color"));
                item.setVehiclePlateNumber(rs.getString("permit_veh_plate_number"));
                state_key.setKey(rs.getInt("permit_veh_plate_state_id"));
                item.setVehiclePlateState(state.get(state_key));

                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermitRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;    }

}

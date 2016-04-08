package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class Permit {

    private Integer id;
    private Batch batch;
    private Unit unit;
    @NotNull(message = "Must have a status")
    private Integer status;
    private Boolean isOpenParking;
    private String assignment;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleColor;
    private String vehiclePlateNumber;
    private State vehiclePlateState;
    private ArrayList<PermitHistory> permitHistory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsOpenParking() {
        return isOpenParking;
    }

    public void setIsOpenParking(Boolean isOpenParking) {
        this.isOpenParking = isOpenParking;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public State getVehiclePlateState() {
        return vehiclePlateState;
    }

    public void setVehiclePlateState(State vehiclePlateState) {
        this.vehiclePlateState = vehiclePlateState;
    }
    
    public void fillUnit(DbContext context) {
        this.unit = (Unit) context.UnitPermit().getByChild(this);
    }
    
    public void fillState(DbContext context) {
        this.vehiclePlateState = (State) context.StatePermit().getByChild(this);
    }
    
    public void fillBatch(DbContext context) {
        this.batch = (Batch) context.BatchPermit().getByChild(this);
    }
    
    public void fillPermitHistory(DbContext context, Key<Integer> key){
        key.setKey(this.id);
        this.setPermitHistory((ArrayList<PermitHistory>) context.PermitPermitHistory().getByParent(this));
    }

    public ArrayList<PermitHistory> getPermitHistory() {
        return permitHistory;
    }

    public void setPermitHistory(ArrayList<PermitHistory> permitHistory) {
        this.permitHistory = permitHistory;
    }

}
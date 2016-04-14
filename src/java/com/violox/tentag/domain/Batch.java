package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class Batch {

    private Integer id;
    @NotNull(message = "Must have a valid property.")
    private Property property;
    private Printer printer;
    @NotNull(message = "Must have a valid Status.")
    private Integer status;
    private ArrayList<BatchHistory> batchHistory;
    private ArrayList<Permit> permits;

    //F Keys
    private Integer propertyId;
    private Integer printerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void fillPrinter(DbContext context) {
        this.printer = (Printer) context.PrinterBatch().getByChild(this);
    }

    public void fillProperty(DbContext context) {
        this.property = (Property) context.PropertyBatch().getByChild(this);
    }

    public void fillBatchHistory(DbContext context, Key<Integer> key) {
        key.setKey(this.id);
        this.setBatchHistory((ArrayList<BatchHistory>) context.BatchBatchHistory().getByParent(this));
    }

    public void fillPermits(DbContext context, Key<Integer> key) {
        key.setKey(this.id);
        this.setPermits((ArrayList<Permit>) context.BatchPermit().getByParent(this));
    }

    public ArrayList<BatchHistory> getBatchHistory() {
        return batchHistory;
    }

    public void setBatchHistory(ArrayList<BatchHistory> batchHistory) {
        this.batchHistory = batchHistory;
    }

    public ArrayList<Permit> getPermits() {
        return permits;
    }

    public void setPermits(ArrayList<Permit> permits) {
        this.permits = permits;
    }

}

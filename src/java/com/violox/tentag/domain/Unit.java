package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class Unit {

    private Integer id;
    private Property property;
    @NotNull(message = "Unit must have a name.")
    private String name;
    private String notes;
    private ArrayList<Permit> permits;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ArrayList<Permit> getPermits() {
        return permits;
    }

    public void setPermits(ArrayList<Permit> permits) {
        this.permits = permits;
    }

    public void fillProperty(DbContext context) {
        this.setProperty((Property) context.PropertyBatch().getByChild(this));
    }

    public void fillPermit(DbContext context, Key<Integer> key) {
        key.setKey(this.id);
        this.setPermits((ArrayList<Permit>) context.UnitPermit().getByParent(this));
    }


}

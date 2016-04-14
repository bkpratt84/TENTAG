package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class Property {

    private Integer id;
    @NotNull(message = "Property Name may not be blank.")
    private String name;
    private Contact contact;
    private MailingAddress mailingAddress;
    private BillingAddress billingAddress;
    private ArrayList<Group> groups;
    private ArrayList<Batch> batches;
    private ArrayList<Unit> units;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public MailingAddress getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(MailingAddress mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Batch> getBatches() {
        return batches;
    }

    public void setBatches(ArrayList<Batch> batches) {
        this.batches = batches;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void fillContact(DbContext context) {
        this.contact = (Contact) context.ContactProperty().getByChild(this);
    }

    public void fillMailingAddress(DbContext context) {
        this.mailingAddress = (MailingAddress) context.MailingAddressProperty().getByChild(this);
    }

    public void fillBillingAddress(DbContext context) {
        this.billingAddress = (BillingAddress) context.BillingAddressProperty().getByChild(this);
    }

    public void fillBatch(DbContext context, Key<Integer> key) {
        key.setKey(this.id);
        this.batches = (ArrayList<Batch>) context.PropertyBatch().getByParent(this);
    }

    public void fillGroups(DbContext context, Key<Integer> obj_key) {
        if (this.id == null) {
            return;
        }
        if (this.groups == null) {
            this.groups = new ArrayList<>();
        } else {
            this.groups.clear();
        }

        for (GroupProperty junction : (ArrayList<GroupProperty>) context.PropertyGroupProperty().getByParent(this)) {
            obj_key.setKey(junction.getGroupId());
            Group child = (Group) context.Group().get(obj_key);
            groups.add(child);
        }
    }
    
    public String toString(){
        return this.name;
    }
}

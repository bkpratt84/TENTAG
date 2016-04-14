package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class Printer {

    private Integer id;
    private Contact contact;
    private MailingAddress address;
    @NotNull(message = "Printer must have name.")
    private String name;
    private Boolean isActive;
    private ArrayList<Group> groups;
    private ArrayList<Batch> batches;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public MailingAddress getAddress() {
        return address;
    }

    public void setAddress(MailingAddress address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void fillContact(DbContext context) {
        this.contact = (Contact) context.ContactPrinter().getByChild(this);
    }

    public void fillAddress(DbContext context) {
        this.address = (MailingAddress) context.MailingAddressPrinter().getByChild(this);
    }

    public void fillBatch(DbContext context, Key<Integer> key) {
        key.setKey(this.id);
        this.batches = (ArrayList<Batch>) context.PrinterBatch().getByParent(this);
    }

    public ArrayList<Batch> getBatches() {
        return batches;
    }

    public void setBatches(ArrayList<Batch> batches) {
        this.batches = batches;
    }
    
    public String toString(){
        return this.name;
    }
}

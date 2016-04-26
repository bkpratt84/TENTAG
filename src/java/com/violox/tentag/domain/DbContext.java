package com.violox.tentag.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DbContext {

    
    // Relations
    @Inject
    private Relation<BillingAddress, Integer> address;

    public Relation Address() {
        return address;
    }
    @Inject
    private Relation<BatchHistory, Integer> batchHistory;

    public Relation BatchHistory() {
        return batchHistory;
    }
    @Inject
    private Relation<Batch, Integer> batch;

    public Relation Batch() {
        return batch;
    }
    @Inject
    private Relation<BatchStatusBitMap, Integer> batchStatusBitMap;

    public Relation BatchStatusBitMap() {
        return batchStatusBitMap;
    }
    @Inject
    private Relation<Contact, Integer> contact;

    public Relation Contact() {
        return contact;
    }
    @Inject
    private Relation<GroupPrinter, IntegerPair> groupPrinter;

    public Relation GroupPrinter() {
        return groupPrinter;
    }
    @Inject
    private Relation<GroupProperty, IntegerPair> groupProperty;

    public Relation GroupProperty() {
        return groupProperty;
    }
    @Inject
    private Relation<Group, Integer> group;

    public Relation Group() {
        return group;
    }
    @Inject
    private Relation<PermitHistory, Integer> permitHistory;

    public Relation PermitHistory() {
        return permitHistory;
    }
    @Inject
    private Relation<Permit, Integer> permit;

    public Relation Permit() {
        return permit;
    }
    @Inject
    private Relation<PermitStatusBitMap, Integer> permitStatusBitMap;

    public Relation PermitStatusBitMap() {
        return permitStatusBitMap;
    }
    @Inject
    private Relation<Printer, Integer> printer;

    public Relation Printer() {
        return printer;
    }
    @Inject
    private Relation<Property, Integer> property;

    public Relation Property() {
        return property;
    }
    @Inject
    private Relation<State, Integer> state;

    public Relation State() {
        return state;
    }
    @Inject
    private Relation<Unit, Integer> unit;

    public Relation Unit() {
        return unit;
    }
    @Inject
    private Relation<UserGroup, IntegerPair> userGroup;

    public Relation UserGroup() {
        return userGroup;
    }
    @Inject
    private Relation<User, Integer> user;

    public Relation User() {
        return user;
    }

    // Relationships
    @Inject
    private Relationship<Printer, MailingAddress> mailingAddressPrinter;

    public Relationship MailingAddressPrinter() {
        return mailingAddressPrinter;
    }
    @Inject
    private Relationship<Property, BillingAddress> billingAddressProperty;

    public Relationship BillingAddressProperty() {
        return billingAddressProperty;
    }
    @Inject
    private Relationship<Property, MailingAddress> mailingAddressProperty;

    public Relationship MailingAddressProperty() {
        return mailingAddressProperty;
    }
    @Inject
    private Relationship<BatchHistory, Batch> batchBatchHistory;

    public Relationship BatchBatchHistory() {
        return batchBatchHistory;
    }
    @Inject
    private Relationship<Permit, Batch> batchPermit;

    public Relationship BatchPermit() {
        return batchPermit;
    }
    @Inject
    private Relationship<Printer, Contact> contactPrinter;

    public Relationship ContactPrinter() {
        return contactPrinter;
    }
    @Inject
    private Relationship<Property, Contact> contactProperty;

    public Relationship ContactProperty() {
        return contactProperty;
    }
    @Inject
    private Relationship<GroupPrinter, Group> groupGroupPrinter;

    public Relationship GroupGroupPrinter() {
        return groupGroupPrinter;
    }
    @Inject
    private Relationship<GroupProperty, Group> groupGroupProperty;

    public Relationship GroupGroupProperty() {
        return groupGroupProperty;
    }
    @Inject
    private Relationship<UserGroup, Group> groupUserGroup;

    public Relationship GroupUserGroup() {
        return groupUserGroup;
    }
    @Inject
    private Relationship<PermitHistory, Permit> permitPermitHistory;

    public Relationship PermitPermitHistory() {
        return permitPermitHistory;
    }
    @Inject
    private Relationship<Batch, Printer> printerBatch;

    public Relationship PrinterBatch() {
        return printerBatch;
    }
    @Inject
    private Relationship<GroupPrinter, Printer> printerGroupPrinter;

    public Relationship PrinterGroupPrinter() {
        return printerGroupPrinter;
    }
    @Inject
    private Relationship<Batch, Property> propertyBatch;

    public Relationship PropertyBatch() {
        return propertyBatch;
    }
    @Inject
    private Relationship<GroupProperty, Property> propertyGroupProperty;

    public Relationship PropertyGroupProperty() {
        return propertyGroupProperty;
    }
    @Inject
    private Relationship<Unit, Property> propertyUnit;

    public Relationship PropertyUnit() {
        return propertyUnit;
    }
    @Inject
    private Relationship<BillingAddress, State> stateBillingAddress;

    public Relationship StateBillingAddress() {
        return stateBillingAddress;
    }

    @Inject
    private Relationship<MailingAddress, State> stateMailingAddress;

    public Relationship StateMailingAddress() {
        return stateMailingAddress;
    }
    @Inject
    private Relationship<Permit, State> statePermit;

    public Relationship StatePermit() {
        return statePermit;
    }
    @Inject
    private Relationship<Permit, Unit> unitPermit;

    public Relationship UnitPermit() {
        return unitPermit;
    }
    @Inject
    private Relationship<UserGroup, User> userUserGroup;

    public Relationship UserUserGroup() {
        return userUserGroup;
    }

    @Inject
    private AttributeFilter<Group, String> groupStringAttributeFilter;

    public AttributeFilter GroupStringAttributeFilter() {
        return groupStringAttributeFilter;
    }
}

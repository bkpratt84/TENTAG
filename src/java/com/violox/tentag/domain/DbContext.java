package com.violox.tentag.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DbContext {

    @Inject
    private Relation<Contact, Integer> contact;
    public Relation Contact() {
        return contact;
    }

    @Inject
    private Relation<Address, Integer> address;
    public Relation Address() {
        return address;
    }

    @Inject
    private Relationship<Printer, Address> addressPrinter;

    public Relationship AddressPrinter() {
        return addressPrinter;
    }

}

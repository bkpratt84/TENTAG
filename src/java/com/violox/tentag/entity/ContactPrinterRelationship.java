package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ContactPrinterRelationship implements Relationship<Printer, Contact> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private ContactRelation parent;
    @Inject
    private PrinterRelation child;

    @Override
    public ArrayList<Printer> getByParent(Contact parent) {
        return child.getByContact(parent);
    }

    @Override
    public Contact getByChild(Printer child) {
        child_key.setKey(child.getId());
        return parent.get(child_key);
    }

}

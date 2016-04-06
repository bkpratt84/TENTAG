package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddressPrinterRelationship implements Relationship<Printer, MailingAddress> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private MailingAddressRelation parent;
    @Inject
    private PrinterRelation child;

    @Override
    public ArrayList<Printer> getByParent(MailingAddress parent) {
        return child.getByAddress(parent);
    }

    @Override
    public MailingAddress getByChild(Printer item) {
        child_key.setKey(item.getAddress().getId());
        return parent.get(child_key);
    }
}

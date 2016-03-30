package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContactPrinterRelationship implements Relationship<Printer, Contact> {

    /**
     * Default constructor
     */
    public ContactPrinterRelationship() {
    }

    @Override
    public ArrayList<Printer> getByParent(Contact parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Contact getByChild(Printer child) {
        // TODO implement here
        return null;
    }

}

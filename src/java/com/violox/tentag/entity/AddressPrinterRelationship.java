package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class AddressPrinterRelationship implements Relationship<Printer, Address> {

    public AddressPrinterRelationship() {
    }

    @Override
    public ArrayList<Printer> getByParent(Address address) {
        // TODO implement here
        return null;
    }

    @Override
    public Address getByChild(Printer printer) {
        // TODO implement here
        return null;
    }

}

package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class ContactPropertyRelationship implements Relationship<Property, Contact> {

    public ContactPropertyRelationship() {
    }

    @Override
    public ArrayList<Property> getByParent(Contact parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Contact getByChild(Property child) {
        // TODO implement here
        return null;
    }

}

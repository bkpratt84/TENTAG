package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddressPropertyRelationship implements Relationship<Property, Address> {

    public AddressPropertyRelationship() {
    }

    public ArrayList<Property> getByParent(Address parent) {
        // TODO implement here
        return null;
    }

    public Address getByChild(Property child) {
        // TODO implement here
        return null;
    }

}

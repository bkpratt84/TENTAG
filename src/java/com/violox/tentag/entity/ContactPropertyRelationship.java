package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ContactPropertyRelationship implements Relationship<Property, Contact> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private ContactRelation parent;
    @Inject
    private PropertyRelation child;

    @Override
    public ArrayList<Property> getByParent(Contact parent) {
        return child.getByContact(parent);
    }

    @Override
    public Contact getByChild(Property child) {
        child_key.setKey(child.getId());
        return parent.get(child_key);
    }

}

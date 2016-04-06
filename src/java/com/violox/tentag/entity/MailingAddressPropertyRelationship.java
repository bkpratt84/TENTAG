package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailingAddressPropertyRelationship implements Relationship<Property, Address> {

    public MailingAddressPropertyRelationship() {
    }

    @Inject
    private Key<Integer> child_key;
    @Inject
    private AddressRelation parent;
    @Inject
    private PropertyRelation child;

    @Override
    public ArrayList<Property> getByParent(Address parent) {
        return child.getByMailingAddress(parent);
    }

    @Override
    public Address getByChild(Property child) {
        child_key.setKey(child.getMailingAddress().getId());
        return parent.get(child_key);
    }

}

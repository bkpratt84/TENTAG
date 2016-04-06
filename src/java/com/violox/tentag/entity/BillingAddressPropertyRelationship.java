package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BillingAddressPropertyRelationship implements Relationship<Property, BillingAddress> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private BillingAddressRelation parent;
    @Inject
    private PropertyRelation child;

    @Override
    public ArrayList<Property> getByParent(BillingAddress parent) {
        return child.getByBillingAddress(parent);
    }

    @Override
    public BillingAddress getByChild(Property child) {
        child_key.setKey(child.getBillingAddress().getId());
        return parent.get(child_key);
    }

}

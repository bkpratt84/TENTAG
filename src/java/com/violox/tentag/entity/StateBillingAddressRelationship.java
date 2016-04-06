package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StateBillingAddressRelationship implements Relationship<BillingAddress, State> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private StateRelation parent;
    @Inject
    private BillingAddressRelation child;

    @Override
    public ArrayList<BillingAddress> getByParent(State parent) {
        return child.getByStateBilling(parent);
    }

    @Override
    public State getByChild(BillingAddress child) {
        child_key.setKey(child.getState().getId());
        return parent.get(child_key);
    }

}

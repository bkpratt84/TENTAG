package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StateAddressRelationship implements Relationship<Address, State> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private StateRelation parent;
    @Inject
    private AddressRelation child;

    @Override
    public ArrayList<Address> getByParent(State parent) {
        return child.getByState(parent);
    }

    @Override
    public State getByChild(Address child) {
        child_key.setKey(child.getState().getId());
        return parent.get(child_key);
    }

}

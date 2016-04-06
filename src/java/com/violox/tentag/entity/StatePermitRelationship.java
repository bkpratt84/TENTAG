package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StatePermitRelationship implements Relationship<Permit, State> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private StateRelation parent;
    @Inject
    private PermitRelation child;

    @Override
    public ArrayList<Permit> getByParent(State parent) {
        return child.getByState(parent);
    }

    @Override
    public State getByChild(Permit child) {
        child_key.setKey(child.getVehiclePlateState().getId());
        return parent.get(child_key);
    }

}

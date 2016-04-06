package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UnitPermitRelationship implements Relationship<Permit, Unit> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private UnitRelation parent;
    @Inject
    private PermitRelation child;


    @Override
    public ArrayList<Permit> getByParent(Unit parent) {
        return child.getByUnit(parent);
    }

    @Override
    public Unit getByChild(Permit child) {
        child_key.setKey(child.getUnit().getId());
        return parent.get(child_key);
    }

}

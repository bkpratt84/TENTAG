package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PermitPermitHistoryRelationship implements Relationship<PermitHistory, Permit> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private PermitRelation parent;
    @Inject
    private PermitHistoryRelation child;

    @Override
    public ArrayList<PermitHistory> getByParent(Permit parent) {
        return child.getByPermit(parent);
    }

    @Override
    public Permit getByChild(PermitHistory child) {
        child_key.setKey(child.getPermit().getId());
        return parent.get(child_key);
    }
}

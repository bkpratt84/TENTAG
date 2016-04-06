package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BatchPermitRelationship implements Relationship<Permit, Batch> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private BatchRelation parent;
    @Inject
    private PermitRelation child;

    @Override
    public ArrayList<Permit> getByParent(Batch parent) {
        return child.getByBatch(parent);
    }

    @Override
    public Batch getByChild(Permit child) {
        child_key.setKey(child.getBatch().getId());
        return parent.get(child_key);
    }

}

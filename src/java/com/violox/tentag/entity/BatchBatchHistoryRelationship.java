package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BatchBatchHistoryRelationship implements Relationship<BatchHistory, Batch> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private BatchRelation parent;
    @Inject
    private BatchHistoryRelation child;

    @Override
    public ArrayList<BatchHistory> getByParent(Batch parent) {
        return child.getByBatch(parent);
    }

    @Override
    public Batch getByChild(BatchHistory child) {
        child_key.setKey(child.getBatch().getId());
        return parent.get(child_key);
    }

}

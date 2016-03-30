package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BatchBatchHistoryRelationship implements Relationship<BatchHistory, Batch> {

    public BatchBatchHistoryRelationship() {
    }

    @Override
    public ArrayList<BatchHistory> getByParent(Batch parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Batch getByChild(BatchHistory child) {
        // TODO implement here
        return null;
    }

}

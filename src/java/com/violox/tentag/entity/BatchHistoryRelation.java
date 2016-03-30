package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BatchHistoryRelation implements Relation<BatchHistory, Integer> {

    public BatchHistoryRelation() {
    }

    @Override
    public BatchHistory post(BatchHistory item) {
        // TODO implement here
        return null;
    }

    @Override
    public BatchHistory get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<BatchHistory> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(BatchHistory item) {
        // TODO implement here
    }

    @Override
    public void delete(BatchHistory item) {
        // TODO implement here
    }

    @Override
    public Integer getKey() {
        // TODO implement here
        return null;
    }

    @Override
    public void setKey(Integer key) {
        // TODO implement here
    }


}
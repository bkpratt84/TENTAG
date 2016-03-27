package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class BatchRelation implements Relation<Batch, Integer> {

    public BatchRelation() {
    }

    @Override
    public Batch post(Batch item) {
        // TODO implement here
        return null;
    }

    @Override
    public Batch get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<Batch> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(Batch item) {
        // TODO implement here
    }

    @Override
    public void delete(Batch item) {
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

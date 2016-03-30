package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BatchStatusBitMapRelation implements Relation<BatchStatusBitMap, Integer>  {

    public BatchStatusBitMapRelation() {
    }

    @Override
    public BatchStatusBitMap post(BatchStatusBitMap item) {
        // TODO implement here
        return null;
    }

    @Override
    public BatchStatusBitMap get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<BatchStatusBitMap> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(BatchStatusBitMap item) {
        // TODO implement here
    }

    @Override
    public void delete(BatchStatusBitMap item) {
        // TODO implement here
    }

    @Override
    public Integer getKey() {
        // TODO implement here
        return null;
    }


    @Override
    public void setKey( Integer key) {
        // TODO implement here
    }

}
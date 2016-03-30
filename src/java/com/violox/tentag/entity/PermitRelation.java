package com.violox.tentag.entity;

import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.Relation;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class PermitRelation implements Relation<Permit, Integer> {

    public PermitRelation() {
    }

    @Override
    public Permit post(Permit item) {
        // TODO implement here
        return null;
    }

    @Override
    public Permit get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<Permit> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(Permit item) {
        // TODO implement here
    }

    @Override
    public void delete(Permit item) {
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
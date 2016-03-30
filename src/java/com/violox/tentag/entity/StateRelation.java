package com.violox.tentag.entity;

import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.Relation;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class StateRelation implements Relation<State, Integer> {

    public StateRelation() {
    }

    @Override
    public State post(State item) {
        // TODO implement here
        return null;
    }

    @Override
    public State get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<State> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(State item) {
        // TODO implement here
    }

    @Override
    public void delete(State item) {
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
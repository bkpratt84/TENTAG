package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropertyRelation implements Relation<Property, Integer> {

    /**
     * Default constructor
     */
    public PropertyRelation() {
    }

    @Override
    public Property post(Property item) {
        // TODO implement here
        return null;
    }

    @Override
    public Property get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<Property> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(Property item) {
        // TODO implement here
    }

    @Override
    public void delete(Property item) {
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

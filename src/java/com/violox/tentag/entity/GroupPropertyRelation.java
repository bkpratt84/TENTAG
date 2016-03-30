package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupPropertyRelation implements Relation<GroupProperty, IntegerPair> {

    public GroupPropertyRelation() {
    }

    @Override
    public GroupProperty post(GroupProperty item) {
        // TODO implement here
        return null;
    }

    @Override
    public GroupProperty get(Key<IntegerPair> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<GroupProperty> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(GroupProperty item) {
        // TODO implement here
    }

    @Override
    public void delete(GroupProperty item) {
        // TODO implement here
    }

    @Override
    public IntegerPair getKey() {
        // TODO implement here
        return null;
    }

    @Override
    public void setKey(IntegerPair key) {
        // TODO implement here
    }

}

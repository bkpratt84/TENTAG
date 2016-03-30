package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupRelation implements Relation<Group, Integer> {

    public GroupRelation() {
    }

    @Override
    public Group post(Group item) {
        // TODO implement here
        return null;
    }

    @Override
    public Group get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<Group> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(Group item) {
        // TODO implement here
    }

    @Override
    public void delete(Group item) {
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

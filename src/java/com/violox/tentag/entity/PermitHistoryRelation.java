package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PermitHistoryRelation implements Relation<PermitHistory, Integer> {

    public PermitHistoryRelation() {
    }

    @Override
    public PermitHistory post(PermitHistory item) {
        // TODO implement here
        return null;
    }

    @Override
    public PermitHistory get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<PermitHistory> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(PermitHistory item) {
        // TODO implement here
    }

    @Override
    public void delete(PermitHistory item) {
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

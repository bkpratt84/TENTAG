package com.violox.tentag.entity;

import com.violox.tentag.domain.IntegerPair;
import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.Relation;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class UserGroupRelation implements Relation<UserGroup, IntegerPair> {

    public UserGroupRelation() {
    }

    @Override
    public UserGroup post(UserGroup item) {
        // TODO implement here
        return null;
    }

    @Override
    public UserGroup get(Key<IntegerPair> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<UserGroup> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(UserGroup item) {
        // TODO implement here
    }

    @Override
    public void delete(UserGroup item) {
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

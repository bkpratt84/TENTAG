package com.violox.tentag.entity;

import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.Relation;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class UserRelation implements Relation<User, Integer> {

    public UserRelation() {
    }

    @Override
    public User post(User item) {
        // TODO implement here
        return null;
    }

    @Override
    public User get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<User> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(User item) {
        // TODO implement here
    }

    @Override
    public void delete(User item) {
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

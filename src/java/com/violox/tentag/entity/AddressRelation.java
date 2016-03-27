package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class AddressRelation implements Relation<Address, Integer> {

    public AddressRelation() {
    }

    @Override
    public Address post(Address item) {
        // TODO implement here
        return null;
    }

    @Override
    public Address get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<Address> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(Address item) {
        // TODO implement here
    }

    @Override
    public void delete(Address item) {
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

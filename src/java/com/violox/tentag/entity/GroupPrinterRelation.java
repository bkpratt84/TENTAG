package com.violox.tentag.entity;

import com.violox.tentag.domain.IntegerPair;
import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.Relation;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class GroupPrinterRelation implements Relation<GroupPrinter, IntegerPair> {

    public GroupPrinterRelation() {
    }

    @Override
    public GroupPrinter post(GroupPrinter item) {
        // TODO implement here
        return null;
    }

    @Override
    public GroupPrinter get(Key<IntegerPair> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<GroupPrinter> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(GroupPrinter item) {
        // TODO implement here
    }

    @Override
    public void delete(GroupPrinter item) {
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

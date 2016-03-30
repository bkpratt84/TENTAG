package com.violox.tentag.entity;

import com.violox.tentag.domain.Relationship;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class StateAddressRelationship implements Relationship<Address, State> {

    public StateAddressRelationship() {
    }

    @Override
    public ArrayList<Address> getByParent(State parent) {
        // TODO implement here
        return null;
    }

    @Override
    public State getByChild(Address child) {
        // TODO implement here
        return null;
    }

}

package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
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

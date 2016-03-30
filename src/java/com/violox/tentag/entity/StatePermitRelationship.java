package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatePermitRelationship implements Relationship<Permit, State> {

    public StatePermitRelationship() {
    }

    @Override
    public ArrayList<Permit> getByParent(State parent) {
        // TODO implement here
        return null;
    }

    @Override
    public State getByChild(Permit child) {
        // TODO implement here
        return null;
    }

}

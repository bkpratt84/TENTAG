package com.violox.tentag.entity;

import com.violox.tentag.domain.Relationship;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class UnitPermitRelationship implements Relationship<Permit, Unit> {

    public UnitPermitRelationship() {
    }

    @Override
    public ArrayList<Permit> getByParent(Unit parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Unit getByChild(Permit child) {
        // TODO implement here
        return null;
    }

}

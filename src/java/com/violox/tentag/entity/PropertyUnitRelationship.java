package com.violox.tentag.entity;

import com.violox.tentag.domain.Relationship;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class PropertyUnitRelationship implements Relationship<Unit, Property> {

    public PropertyUnitRelationship() {
    }

    @Override
    public ArrayList<Unit> getByParent(Property parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Property getByChild(Unit child) {
        // TODO implement here
        return null;
    }

}

package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
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

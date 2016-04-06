package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PropertyUnitRelationship implements Relationship<Unit, Property> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private PropertyRelation parent;
    @Inject
    private UnitRelation child;

    @Override
    public ArrayList<Unit> getByParent(Property parent) {
        return child.getByProperty(parent);
    }

    @Override
    public Property getByChild(Unit child) {
        child_key.setKey(child.getProperty().getId());
        return parent.get(child_key);
    }

}

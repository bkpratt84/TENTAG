package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PropertyBatchRelationship implements Relationship<Batch, Property> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private PropertyRelation parent;
    @Inject
    private BatchRelation child;

    @Override
    public ArrayList<Batch> getByParent(Property parent) {
        return child.getByProperty(parent);
    }

    @Override
    public Property getByChild(Batch child) {
        child_key.setKey(child.getPropertyId());
        return parent.get(child_key);
    }

}

package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PropertyGroupPropertyRelationship implements Relationship<GroupProperty, Property> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private PropertyRelation parent;
    @Inject
    private GroupPropertyRelation child;

    @Override
    public ArrayList<GroupProperty> getByParent(Property parent) {
        return child.getByProperty(parent);
    }

    @Override
    public Property getByChild(GroupProperty child) {
        child_key.setKey(child.getProperty().getId());
        return parent.get(child_key);
    }

}

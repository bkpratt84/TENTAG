package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropertyGroupPropertyRelationship implements Relationship<GroupProperty, Property> {

    public PropertyGroupPropertyRelationship() {
    }

    @Override
    public ArrayList<GroupProperty> getByParent(Property parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Property getByChild(GroupProperty child) {
        // TODO implement here
        return null;
    }

}

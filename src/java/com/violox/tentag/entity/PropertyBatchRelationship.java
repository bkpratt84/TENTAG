package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropertyBatchRelationship implements Relationship<Batch, Property> {

    public PropertyBatchRelationship() {
    }

    @Override
    public ArrayList<Batch> getByParent(Property parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Property getByChild(Batch child) {
        // TODO implement here
        return null;
    }

}

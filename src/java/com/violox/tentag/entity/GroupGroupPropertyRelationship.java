package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GroupGroupPropertyRelationship implements Relationship<GroupProperty, Group> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private GroupRelation parent;
    @Inject
    private GroupPropertyRelation child;

    @Override
    public ArrayList<GroupProperty> getByParent(Group parent) {
        return child.getByGroup(parent);
    }

    @Override
    public Group getByChild(GroupProperty child) {
        child_key.setKey(child.getGroup().getId());
        return parent.get(child_key);
    }

}

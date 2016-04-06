package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GroupGroupPrinterRelationship implements Relationship<GroupPrinter, Group> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private GroupRelation parent;
    @Inject
    private GroupPrinterRelation child;

    @Override
    public ArrayList<GroupPrinter> getByParent(Group parent) {
        return child.getByGroup(parent);
    }

    @Override
    public Group getByChild(GroupPrinter child) {
        child_key.setKey(child.getGroup().getId());
        return parent.get(child_key);
    }

}

package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupGroupPropertyRelationship implements Relationship<GroupProperty, Group> {

    public GroupGroupPropertyRelationship() {
    }

    @Override
    public ArrayList<GroupProperty> getByParent(Group parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Group getByChild(GroupProperty child) {
        // TODO implement here
        return null;
    }

}

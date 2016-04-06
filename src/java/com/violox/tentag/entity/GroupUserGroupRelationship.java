package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GroupUserGroupRelationship implements Relationship<UserGroup, Group> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private GroupRelation parent;
    @Inject
    private UserGroupRelation child;


    @Override
    public ArrayList<UserGroup> getByParent(Group parent) {
        return child.getByUserGroup(parent);
    }

    @Override
    public Group getByChild(UserGroup child) {
        child_key.setKey(child.getGroup().getId());
        return parent.get(child_key);
    }

}

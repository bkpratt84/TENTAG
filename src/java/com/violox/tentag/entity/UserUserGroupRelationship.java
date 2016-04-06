package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserUserGroupRelationship implements Relationship<UserGroup, User> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private UserRelation parent;
    @Inject
    private UserGroupRelation child;

    @Override
    public ArrayList<UserGroup> getByParent(User parent) {
        return child.getByUser(parent);
    }

    @Override
    public User getByChild(UserGroup child) {
        child_key.setKey(child.getUser().getId());
        return parent.get(child_key);
    }

}

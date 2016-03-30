package com.violox.tentag.entity;

import com.violox.tentag.domain.Relationship;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class UserUserGroupRelationship implements Relationship<UserGroup, User> {

    public UserUserGroupRelationship() {
    }

    @Override
    public ArrayList<UserGroup> getByParent(User parent) {
        // TODO implement here
        return null;
    }

    @Override
    public User getByChild(UserGroup child) {
        // TODO implement here
        return null;
    }

}

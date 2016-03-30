package com.violox.tentag.entity;

import com.violox.tentag.domain.Relationship;
import com.violox.tentag.domain.*;
import java.util.ArrayList;

public class GroupUserGroupRelationship implements Relationship<UserGroup, Group> {

    public GroupUserGroupRelationship() {
    }

    @Override
    public ArrayList<UserGroup> getByParent(Group parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Group getByChild(UserGroup child) {
        // TODO implement here
        return null;
    }

}

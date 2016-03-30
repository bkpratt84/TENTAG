package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupGroupPrinterRelationship implements Relationship<GroupPrinter, Group> {

    // TODO:  This is a many to many relationship, so special crap needs to be done here
    
    public GroupGroupPrinterRelationship() {
    }

    @Override
    public ArrayList<GroupPrinter> getByParent(Group parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Group getByChild(GroupPrinter child) {
        // TODO implement here
        return null;
    }

}

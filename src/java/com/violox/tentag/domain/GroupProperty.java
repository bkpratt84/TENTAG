package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GroupProperty {

    private Group group;
    private Property property;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

}

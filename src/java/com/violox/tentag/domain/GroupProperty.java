package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GroupProperty {

    private Integer groupId;
    private Integer propertyId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

}

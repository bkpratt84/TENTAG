package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GroupPrinter {

    private Integer groupId;
    private Integer printerId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

}

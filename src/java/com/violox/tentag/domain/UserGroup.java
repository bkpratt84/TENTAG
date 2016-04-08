package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;

@RequestScoped

public class UserGroup {
    private Integer userId;
    private Integer groupId;
    private String userName;
    private String roleName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String groupName) {
        this.roleName = groupName;
    }
    
    
    
}

package com.violox.tentag.domain;

import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.userId);
        hash = 79 * hash + Objects.hashCode(this.groupId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserGroup other = (UserGroup) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.groupId, other.groupId)) {
            return false;
        }
        return true;
    }
}

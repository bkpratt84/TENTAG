package com.violox.tentag.domain;

import java.util.ArrayList;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class User {

    private Integer id;
    @NotNull(message = "User must have a name.")
    private String name;
    @NotNull(message = "User must have a password.")
    private String password;
    @Pattern(regexp = "(\bAdmin\b|\bPrinter\b|\bProperty\b)", message = "Role must be Admin, Printer, or Property.")
    private String role;
    private ArrayList<Group> groups;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
    }

    public void fillGroups(DbContext context, Key<Integer> obj_key) {
        if (this.id == null) {
            return;
        }
        if (this.groups == null) {
            this.groups = new ArrayList<>();
        } else {
            this.groups.clear();
        }

        for (UserGroup ug : (ArrayList<UserGroup>)context.UserUserGroup().getByParent(this)) {
            obj_key.setKey(ug.getGroupId());
            Group g = (Group) context.Group().get(obj_key);
            groups.add(g);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }
}

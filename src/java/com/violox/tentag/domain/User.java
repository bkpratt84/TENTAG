package com.violox.tentag.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class User implements Serializable {

    private Integer id;
    @Size(min = 4, max = 50, message = "Username between 4 and 50 characters.")
    private String name;
    @Size(min = 8, max = 20, message = "Password between 8 and 20 characters.")
    private String password;
    //@Pattern(regexp = "(\bAdmin\b|\bPrinter\b|\bProperty\b)", message = "Role must be Admin, Printer, or Property.")
    @NotNull(message = "Select a role.")
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}

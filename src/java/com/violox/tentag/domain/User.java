package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class User {

    private Integer id;
    @NotNull(message = "User must have a name.")
    private String name;
    @NotNull(message = "User must have a password.")
    private String password;
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

}

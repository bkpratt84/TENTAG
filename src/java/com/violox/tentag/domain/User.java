package com.violox.tentag.domain;

import java.io.Serializable;
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
    
    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
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
        this.groups = groups;
    }

}
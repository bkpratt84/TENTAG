package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class Group {

    private Integer id;
    @NotNull(message = "Must have a group name.")
    private String name;
    private ArrayList<User> users;
    private ArrayList<Property> properties;
    private ArrayList<Printer> printers;
    @NotNull(message = "Must have a role name.")
    private String role;

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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public ArrayList<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(ArrayList<Printer> printers) {
        this.printers = printers;
    }
    
     public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.name = role;
    }
   

}
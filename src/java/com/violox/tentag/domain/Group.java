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
        this.role = role;
    }

    public void fillPrinters(DbContext context, Key<Integer> obj_key) {
        if (this.id == null) {
            return;
        }
        if (this.printers == null) {
            this.printers = new ArrayList<>();
        } else {
            this.printers.clear();
        }

        for (GroupPrinter junction : (ArrayList<GroupPrinter>) context.GroupGroupPrinter().getByParent(this)) {
            obj_key.setKey(junction.getGroupId());
            Printer child = (Printer) context.Printer().get(obj_key);
            printers.add(child);
        }
    }

    public void fillProperties(DbContext context, Key<Integer> obj_key) {
        if (this.id == null) {
            return;
        }
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        } else {
            this.properties.clear();
        }

        for (GroupProperty junction : (ArrayList<GroupProperty>) context.GroupGroupProperty().getByParent(this)) {
            obj_key.setKey(junction.getGroupId());
            Property child = (Property) context.Property().get(obj_key);
            properties.add(child);
        }
    }

    public void fillUsers(DbContext context, Key<Integer> obj_key) {
        if (this.id == null) {
            return;
        }
        if (this.users == null) {
            this.users = new ArrayList<>();
        } else {
            this.users.clear();
        }

        for (UserGroup junction : (ArrayList<UserGroup>) context.GroupUserGroup().getByParent(this)) {
            obj_key.setKey(junction.getGroupId());
            User child = (User) context.User().get(obj_key);
            users.add(child);
        }
    }

}

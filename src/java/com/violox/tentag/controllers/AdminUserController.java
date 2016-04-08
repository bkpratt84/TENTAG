package com.violox.tentag.controllers;

import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "adminUserController")
@ViewScoped
public class AdminUserController implements Serializable {
    @Inject
    private DbContext dbcontext;
    
    private ArrayList<User> users;
    
    @PostConstruct
    public void init() {
        if (users == null) {
            users = new ArrayList<>();
            
//            users.add(new User(1, "Admin", "Admin"));
//            users.add(new User(2, "Jesus", "Admin"));
//            users.add(new User(3, "Ralph", "Admin"));
//            users.add(new User(4, "Mary", "Admin"));
//            users.add(new User(5, "Joseph", "Admin"));
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
}
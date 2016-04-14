package com.violox.tentag.controllers;

import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named(value = "adminUserController")
@SessionScoped
public class AdminUserController implements Serializable {
    private static final Logger logger = Logger.getLogger("AdminUserController");

    @Inject
    private Key<Integer> obj_key;
    
    @Inject
    private DbContext dbcontext;
    
    @Inject
    private NavigationController nav;
    
    private ArrayList<User> users;
    
    private User selectedUser, origSelectedUser;
    
    @PostConstruct
    public void init() {
        if (users == null) {
            users = dbcontext.User().get();

            for (User u : users) {
                u.fillGroups(dbcontext, obj_key);
            }
        }
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    public String getUserRole(User user) {
        if (users == null) {
            return null;
        } else {
            //return user.getGroups().get(0).getRole();
            return user.getRole();
        }
    }
    
    public void addUser() {
        
    }
    
    public void DFeditUser() {
        Map<String,Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("height", 180);
        options.put("width", 450);
        options.put("contentWidth", 400);
        options.put("contentHeight", 175);
        
        origSelectedUser = selectedUser;
        RequestContext.getCurrentInstance().openDialog(nav.toAdminUsersEditUser(false, false), options, null);
    }
    
    public void DFsaveUser() {
        logger.info("test");
        if (!selectedUser.equals(origSelectedUser)) {
            dbcontext.User().put(selectedUser);
        }
        
        RequestContext.getCurrentInstance().closeDialog(null);
    }
}
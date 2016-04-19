package com.violox.tentag.controllers;

import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.Group;
import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.User;
import com.violox.tentag.domain.UserGroup;
import com.violox.tentag.utils.Messages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Named(value = "adminUserController")
@ViewScoped
public class AdminUserController implements Serializable {
    private static final Logger logger = Logger.getLogger("AdminUserController");

    @Inject
    private Key<Integer> obj_key;
    
    @Inject
    private DbContext dbcontext;
    
    private ArrayList<User> users;
    
    private User user;
    
    @Inject
    private User newUser;
    
    @PostConstruct
    public void init() {
        if (users == null) {
            refreshData();
        }
    }
    
    public void refreshData() {
        users = dbcontext.User().get();

        for (User u : users) {
            u.fillGroups(dbcontext, obj_key);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {   
        this.user = user;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    public String getUserRole(User user) {
        if (users == null) {
            return null;
        } else {
            return user.getRole();
        }
    }
    
    public void deleteUser() {
        //Delete groups user is in
        for (Group g : user.getGroups()) {
            UserGroup ug = new UserGroup();
            ug.setGroupId(g.getId());
            ug.setUserId(user.getId());

            dbcontext.UserGroup().delete(ug);
        }
        
        //Delete user
        dbcontext.User().delete(user);
        
        user = null;
        refreshData();
        Messages.setSuccessMessage("User Deleted!", null);
        RequestContext.getCurrentInstance().update("form_errors");
    }
    
    public void dgAddUser() {
        dbcontext.User().post(newUser);
        
        newUser = null;
        refreshData();
        RequestContext.getCurrentInstance().execute("PF('dgAdd').hide();");
        Messages.setSuccessMessage("User Added!", null);
        RequestContext.getCurrentInstance().update("form_errors"); 
    }
    
    public void dgEditUser() {
        dbcontext.User().put(user);

        //Delete groups that don't match users role
        for (Group g : user.getGroups()) {
            if (!g.getRole().equals(user.getRole())) {
                UserGroup ug = new UserGroup();
                ug.setGroupId(g.getId());
                ug.setUserId(user.getId());
                
                dbcontext.UserGroup().delete(ug);
            }
        }
        
        user = null;
        RequestContext.getCurrentInstance().execute("PF('dgSave').hide();");
        Messages.setSuccessMessage("Changes Saved!", null);
        RequestContext.getCurrentInstance().update("form_errors");
    }
}

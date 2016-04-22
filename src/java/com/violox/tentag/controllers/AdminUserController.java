package com.violox.tentag.controllers;

import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.Group;
import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.User;
import com.violox.tentag.domain.UserGroup;
import com.violox.tentag.domain.md5Hash;
import com.violox.tentag.utils.Messages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import org.primefaces.context.RequestContext;

@Named(value = "adminUserController")
@ViewScoped
public class AdminUserController implements Serializable {
    private static final Logger logger = Logger.getLogger("AdminUserController");

    @Inject
    private Key<Integer> obj_key;
    
    @Inject
    private DbContext dbcontext;
    
    @Inject
    private LoginController login;
    
    private ArrayList<User> users;
    private User user;
    private boolean display;
    
    @Inject
    private User newUser;
    
    @Size(min = 8, max = 20, message = "Password between 8 and 20 characters.")
    String pw1, pw2;
    
    @PostConstruct
    public void init() {
        display = false;
        
        refreshData();
    }
    
    public void refreshData() {
        users = dbcontext.User().get();

        for (User u : users) {
            u.fillGroups(dbcontext, obj_key);
        }
        
        display = false;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getPw1() {
        return pw1;
    }

    public void setPw1(String pw1) {
        this.pw1 = pw1;
    }

    public String getPw2() {
        return pw2;
    }

    public void setPw2(String pw2) {
        this.pw2 = pw2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        display = true;
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
        boolean success;
        String msg;
        
        if (user.getName().toLowerCase().equals(login.getUsername().toLowerCase())) {
            success = false;
            msg = "Can't delete yourself!";
        } else {
            //Delete groups user is in
            for (Group g : user.getGroups()) {
                UserGroup ug = new UserGroup();
                ug.setGroupId(g.getId());
                ug.setUserId(user.getId());

                dbcontext.UserGroup().delete(ug);
            }

            //Delete user
            dbcontext.User().delete(user);
            success = true;
            msg = "User Deleted!";
        }
        
        refreshData();
        if (success) {
            Messages.setSuccessMessage(msg, null);
        } else {
            Messages.setErrorMessage(msg, null);
        }
        RequestContext.getCurrentInstance().update("form_errors");
    }
    
    public void dgAddUser() {
        newUser.setPassword(md5Hash.hash(newUser.getPassword()));
        dbcontext.User().post(newUser);
        
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
        
        refreshData();
        RequestContext.getCurrentInstance().execute("PF('dgSave').hide();");
        Messages.setSuccessMessage("Changes Saved!", null);
        RequestContext.getCurrentInstance().update("form_errors");
    }
    
    public void dgResetPW() {
        user.setPassword(md5Hash.hash(pw1));
        dbcontext.User().put(user);
        
        pw1 = null;
        pw2 = null;
        
        refreshData();
        RequestContext.getCurrentInstance().execute("PF('dgResetPW').hide();");
        Messages.setSuccessMessage("Password reset!", null);
        RequestContext.getCurrentInstance().update("form_errors");
    }
}
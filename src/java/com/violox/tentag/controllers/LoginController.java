package com.violox.tentag.controllers;

import com.violox.tentag.utils.Messages;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {
    @Inject
    NavigationController nav;
    
    //private static final Logger logger = Logger.getLogger("LoginController");
    private String username;
    private boolean loggedIn;
    private String role;
    
    public String logout() {
        loggedIn = false;
        username = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        Messages.setSuccessMessage("You have signed out.");
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        
        return nav.toHome(true);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
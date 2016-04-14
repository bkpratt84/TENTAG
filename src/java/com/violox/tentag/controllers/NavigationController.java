package com.violox.tentag.controllers;

import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "navigationController")
@RequestScoped
public class NavigationController {
    private static final Logger logger = Logger.getLogger("NavigationController");
    
    public String toAdmin(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/admin/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toAdminHome(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/admin/home.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
 
    public String toAdminUsers(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/admin/users.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
 
    public String toAdminUsersEditUser(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/admin/users/edit";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
     
    public String toAdminBatches(boolean redirect) {
        String url = "/admin/batches.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toPrinter(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/printer/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toProperty(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toHome(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toLogin(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/login/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }    
}
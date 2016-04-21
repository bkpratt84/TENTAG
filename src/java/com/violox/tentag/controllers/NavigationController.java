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
        url += "/admin/users/editUser.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toAdminUsersAddUser(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/admin/users/addUser.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toAdminUsersResetPW(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/admin/users/resetPW.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toAdminUserGroups(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/admin/userGroups.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toAdminBatches(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/admin/batches.xhtml";
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

    
    public String toPropertyHome(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/home.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toPropertyUnits(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/units.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toPropertyPermits(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/permits.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toPropertyBatches(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/batches.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
//
//    public String toPropertyUsersEditUser(boolean redirect, boolean includeContext) {
//        String url = includeContext ? "/TENTAG" : "";
//        url += "/property/users/editUser.xhtml";
//        return redirect ? (url + "?faces-redirect=true") : url;
//    }
//
//    public String toPropertyUsersAddUser(boolean redirect, boolean includeContext) {
//        String url = includeContext ? "/TENTAG" : "";
//        url += "/property/users/addUser.xhtml";
//        return redirect ? (url + "?faces-redirect=true") : url;
//    }
//
//    public String toPropertyUsersResetPW(boolean redirect, boolean includeContext) {
//        String url = includeContext ? "/TENTAG" : "";
//        url += "/property/users/resetPW.xhtml";
//        return redirect ? (url + "?faces-redirect=true") : url;
//    }
//
//    public String toPropertyUserGroups(boolean redirect, boolean includeContext) {
//        String url = includeContext ? "/TENTAG" : "";
//        url += "/property/userGroups.xhtml";
//        return redirect ? (url + "?faces-redirect=true") : url;
//    }
//
    
    public String toPrinterHome(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/printer/home.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toPrinterBatches(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/printer/batches.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toPropertyPermitsEditPermit(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/home.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toPropertyPermitsAddPermit(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/home.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

        public String toPropertyUnitsEditUnit(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/home.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }

    public String toPropertyUnitsAddUnit(boolean redirect, boolean includeContext) {
        String url = includeContext ? "/TENTAG" : "";
        url += "/property/home.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }


    
}

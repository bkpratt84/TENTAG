package com.violox.tentag.controllers;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "navigationController")
@RequestScoped
public class NavigationController {
    public String toAdmin(boolean redirect) {
        String url = "/admin/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toPrinter(boolean redirect) {
        String url = "/Printer/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toProperty(boolean redirect) {
        String url = "/property/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toHome(boolean redirect) {
        String url = "/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
    
    public String toLogin(boolean redirect) {
        String url = "/login/index.xhtml";
        return redirect ? (url + "?faces-redirect=true") : url;
    }
}
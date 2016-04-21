package com.violox.tentag.controllers;

import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.UserGroup;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "propertyUserGroupController")
@ViewScoped
public class PropertyPermitController implements Serializable {

    @Inject
    private DbContext dbcontext;
    
    private ArrayList<UserGroup> ugs;
    private UserGroup ug;
    
    private boolean display;
    
    @PostConstruct
    public void init() {
        display = false;
        
        refreshData();
    }
    
    public void refreshData() {
        ugs = dbcontext.UserGroup().get();
    }

    public ArrayList<UserGroup> getUgs() {
        return ugs;
    }

    public void setUgs(ArrayList<UserGroup> ugs) {
        this.ugs = ugs;
    }

    public UserGroup getUg() {
        return ug;
    }

    public void setUg(UserGroup ug) {
        this.ug = ug;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
    
    
}
package com.violox.tentag.controllers;

import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.Permit;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "propertyPermitController")
@ViewScoped
public class PropertyPermitController implements Serializable {

    @Inject
    private DbContext dbcontext;

    private ArrayList<Permit> permits;
    private Permit permit;

    private boolean display;

    @PostConstruct
    public void init() {
        display = false;

        refreshData();
    }

    public void refreshData() {
        permits = dbcontext.Permit().get();
    }

    public ArrayList<Permit> getPermits() {
        return permits;
    }

    public void setPermits(ArrayList<Permit> permits) {
        this.permits = permits;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public void deletePermit() {
        dbcontext.Permit().delete(permit);
    }

}

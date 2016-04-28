package com.violox.tentag.controllers;

import com.violox.tentag.domain.*;
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

@Named(value = "propertyUnitController")
@ViewScoped
public class PropertyUnitController implements Serializable {

    private static final Logger logger = Logger.getLogger("PropertyUnitController");

    @Inject
    private Key<Integer> obj_key;

    @Inject
    private DbContext dbcontext;

    @Inject
    private Unit newUnit;

    @Inject
    private LoginController lc;

    private ArrayList<Property> properties;

    private ArrayList<Unit> units;
    private Unit unit;
    private boolean display;

    @PostConstruct
    public void init() {
        display = false;

        refreshData();
    }

    public void refreshData() {
        units = dbcontext.Unit().get();

        if (properties == null) {
            properties = new ArrayList<>();
        } else {
            properties.clear();
        }
        User u = new User();
        u.setName(lc.getUsername());

        u = (User) dbcontext.User().getByAlternateKey(u);
        u.fillGroups(dbcontext, obj_key);
        for (Group g : u.getGroups()) {
            g.fillProperties(dbcontext, obj_key);
            for (Property p : g.getProperties()) {
                properties.add(p);
            }
        }

        display = false;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        display = true;
        this.unit = unit;
    }

    public Unit getNewUnit() {
        return newUnit;
    }

    public void setNewUnit(Unit newUnit) {
        this.newUnit = newUnit;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void dgAddUnit() {
        dbcontext.Unit().post(newUnit);

        refreshData();
        RequestContext.getCurrentInstance().execute("PF('dgAdd').hide();");
        Messages.setSuccessMessage("Unit Added!", null);
        RequestContext.getCurrentInstance().update("form_errors");
    }

    public void dgEditUnit() {
        dbcontext.Unit().put(unit);

        refreshData();
        RequestContext.getCurrentInstance().execute("PF('dgSave').hide();");
        Messages.setSuccessMessage("Changes Saved!", null);
        RequestContext.getCurrentInstance().update("form_errors");
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

}

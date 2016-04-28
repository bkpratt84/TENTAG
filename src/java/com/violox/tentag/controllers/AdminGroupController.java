package com.violox.tentag.controllers;

import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.Group;
import com.violox.tentag.domain.Key;
import com.violox.tentag.domain.User;
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

@Named(value = "adminGroupController")
@ViewScoped
public class AdminGroupController implements Serializable {
    private static final Logger logger = Logger.getLogger("AdminGroupController");
    
    @Inject
    private Key<Integer> key;
        
    @Inject
    private DbContext dbcontext;
    
    private ArrayList<Group> groups;
    private Group group;
    
    private boolean display;
    
    @Size(min = 5, max = 50, message="Name between 5 and 50 characters.")
    private String groupName;
    private String role;
    
    @PostConstruct
    public void init() {  
        refreshData();
    }
    
    public void refreshData() {
        groupName = null;
        role = null;
        groups = dbcontext.Group().get();
        display = false;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        display = true;
        this.group = group;
    }
    
    public void deleteGroup() {
        boolean success;
        String msg;
        
        group.fillUsers(dbcontext, key);
        
        if (group.getUsers().isEmpty()) {
            dbcontext.Group().delete(group);
            msg = "Group Deleted!";
            success = true;
        } else {
            msg = "Unable to delete, records associated with this group.";
            success = false;
        }
        
        refreshData();
        if (success) {
            Messages.setSuccessMessage(msg, null);
        } else {
            Messages.setErrorMessage(msg, null);
        }
        RequestContext.getCurrentInstance().update("form_errors");
    }
    
    public void dgAddGroup() {
        Group g = new Group();
        g.setName(groupName);
        g.setRole(role);
        
        dbcontext.Group().post(g);
        
        refreshData();
        RequestContext.getCurrentInstance().execute("PF('dgAdd').hide();");
        Messages.setSuccessMessage("Group Added!", null);
        RequestContext.getCurrentInstance().update("form_errors");
    }
}
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

@Named(value = "adminUserGroupController")
@ViewScoped
public class AdminUserGroupController implements Serializable {
    private static final Logger logger = Logger.getLogger("AdminUserGroupController");
    
    @Inject
    private DbContext dbcontext;
    
    @Inject
    private Key<Integer> key;
    
    @Inject
    private User selUser;
    
    @Inject
    private Group selGroup;
    
    private ArrayList<UserGroup> ugs;
    private UserGroup ug;
    private ArrayList<User> users;
    private ArrayList<Group> groups;
    
    private boolean display;
    
    @PostConstruct
    public void init() {  
        refreshData();
    }
    
    public void refreshData() {
        ugs = dbcontext.UserGroup().get();
        loadUsers();
        selUser = null;
        selGroup = null;
        display = false;
    }
    
    private void loadUsers() {
        ArrayList<User> temp = dbcontext.User().get();
        users = new ArrayList<>();
        
        for (User u : temp) {
            if (!u.getRole().equals("Admin")) {
                users.add(u);
            }
        }
    }
    
    public ArrayList<User> getUsers() {
        return users; 
    }
    
    public ArrayList<Group> getSelGroups() {
        return groups;
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
        display = true;
        this.ug = ug;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public User getSelUser() {
        return selUser;
    }

    public void setSelUser(User selUser) {
        this.selUser = selUser;
    }
    
    public String getGroupName(int ID) {
        key.setKey(ID);
        Group group = (Group) dbcontext.Group().get(key);
        
        return group.getName();
    }
    
    public String getUserName(int ID) {
        key.setKey(ID);
        
        User user = (User) dbcontext.User().get(key);
        
        return user.getName();
    }

    public Group getSelGroup() {
        return selGroup;
    }

    public void setSelGroup(Group selGroup) {
        this.selGroup = selGroup;
    }
    
    public void deleteUserGroup() {
        String msg;
        msg = "Usergroup Deleted!";
        dbcontext.UserGroup().delete(ug);
        
        refreshData();
        Messages.setSuccessMessage(msg, null);
        RequestContext.getCurrentInstance().update("form_errors");
    }
    
    public void dgAddUsergroup() {
        UserGroup g = new UserGroup();
        g.setGroupId(selGroup.getId());
        g.setUserId(selUser.getId());
        g.setUserName(selUser.getName());
        dbcontext.UserGroup().post(g);
        
        refreshData();
        RequestContext.getCurrentInstance().execute("PF('dgAdd').hide();");
        Messages.setSuccessMessage("Usergroup Added!", null);
        RequestContext.getCurrentInstance().update("form_errors");
    }
    
    public void onUserSelect() {
        if (selUser != null) {
            boolean found;
            ArrayList<Group> temp;
            groups = new ArrayList<>();
            
            temp = (ArrayList<Group>) dbcontext.GroupStringAttributeFilter().getByAttribute("g.role_name", selUser.getRole());
            
            for (Group g : temp) {
                found = false;
                for (UserGroup o : ugs) {
                    if (o.getGroupId().equals(g.getId()) && o.getUserId().equals(selUser.getId())) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    groups.add(g);
                }
            }
        }
    }
}
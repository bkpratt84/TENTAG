/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.violox.tentag.controllers;

import com.violox.tentag.domain.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gordon
 */
@Named
@SessionScoped
public class dbtestController implements Serializable {

    @Inject
    private DbContext context;
    @Inject
    private Key<Integer> test_obj_key;

    /* Object under test */
    private Contact contact;
    private ArrayList<Contact> contacts;

    private Group group;
    private ArrayList<Group> groups;

    private User user;
    private ArrayList<User> users;

    private UserGroup usergroup;

    @PostConstruct
    public void init() {
        if (contact == null) {
            contact = new Contact();

            contact.setEmail("test@test.com");
            contact.setFirstName("Testie");
            contact.setLastName("Testerman");
            contact.setPrimaryPhone("123-123-1234");
            contact.setSecondaryPhone("123-123-1235");

            /* Create - post*/
            contact = (Contact) context.Contact().post(contact);
        }

        if (user == null) {

            group = new Group();
            group.setName("PlasticTards");
            group.setRole("printer");

            user = new User();
            user.setName("test_user");
            user.setPassword(md5Hash.hash("password"));
            user.setGroups(groups);

            /* Create - post*/
            group = (Group) context.Group().post(group);
            user = (User) context.User().post(user);

            usergroup = new UserGroup();
            usergroup.setGroupId(group.getId());
            usergroup.setUserId(user.getId());
            usergroup.setUserName(user.getName());
            usergroup.setRoleName(group.getRole());
            context.UserGroup().post(usergroup);

        }

    }

    public User getUser() {

        /* Read - get */
//        test_obj_key.setKey(user.getId());
//        user = (User) context.User().get(test_obj_key);
        user = (User) context.User().getByAlternateKey(user);

        ArrayList<Group> users_groups = new ArrayList<>();
        for (UserGroup ug : (ArrayList<UserGroup>) context.UserUserGroup().getByParent(user)) {
            test_obj_key.setKey(ug.getGroupId());
            users_groups.add((Group) context.Group().get(test_obj_key));
        }
        user.setGroups(users_groups);
        return user;
    }

    public void setUser(User test_obj) {
    }

    public void updateUser() {
        /* Update - put */
        user.setName("test_user_rename");
        context.User().put(user);
    }

    public void destroyUser() {
        /* Update - put */
        context.User().delete(user);
    }

    public ArrayList<User> getUsers() {
        users = context.User().get();
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        //placeholder
    }

    public Group getGroup() {
        /* Read - get */
        test_obj_key.setKey(group.getId());
        group = (Group) context.Group().get(test_obj_key);
        return group;
    }

    public void setGroup(Group test_obj) {
    }

    public void updateGroup() {
        /* Update - put */
        group.setName("test_group_rename");
        context.Group().put(group);
    }

    public void destroyGroup() {
        /* Update - put */
        context.Group().delete(group);
    }

    public ArrayList<Group> getGroups() {
        groups = context.Group().get();
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        //placeholder
    }

    public void destroyUserGroup() {
        /* Update - put */
        context.UserGroup().delete(usergroup);
    }

    public Contact getContact() {
        /* Read - get */
        test_obj_key.setKey(contact.getId());
        contact = (Contact) context.Contact().get(test_obj_key);

        return contact;
    }

    public void setContact(Contact test_obj) {
    }

    public void updateContact() {
        /* Update - put */
        contact.setEmail("newtest@test.com");
        context.Contact().put(contact);
    }

    public void destroyContact() {
        /* Update - put */
        context.Contact().delete(contact);
    }

    public ArrayList<Contact> getContacts() {
        contacts = context.Contact().get();
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contact) {
        //placeholder
    }

}

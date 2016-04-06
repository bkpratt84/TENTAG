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
import javax.enterprise.context.RequestScoped;
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
    private Contact test_obj;
    private ArrayList<Contact> test_objs;

    @PostConstruct
    public void init() {
        if (test_obj == null) {
            test_obj = new Contact();

            test_obj.setEmail("test@test.com");
            test_obj.setFirstName("Testie");
            test_obj.setLastName("Testerman");
            test_obj.setPrimaryPhone("123-123-1234");
            test_obj.setSecondaryPhone("123-123-1235");

            /* Create - post*/
            test_obj = (Contact) context.Contact().post(test_obj);

        }
    }

    public Contact getObject() {

        /* Read - get */
        test_obj_key.setKey(test_obj.getId());
        test_obj = (Contact) context.Contact().get(test_obj_key);

        return test_obj;
    }

    public void setObject(Contact test_obj) {
    }

    public void updateObject() {
        /* Update - put */
        test_obj.setEmail("newtest@test.com");
        context.Contact().put(test_obj);
    }

    public void destroyObject() {
        /* Update - put */
        context.Contact().delete(test_obj);
    }

    public ArrayList<Contact> getObjects() {
        test_objs = (ArrayList<Contact>) context.Contact().get();
        return test_objs;
    }

    public void setObjects(ArrayList<Contact> contact) {
        //placeholder
    }

}

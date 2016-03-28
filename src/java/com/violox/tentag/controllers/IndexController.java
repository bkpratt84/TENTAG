package com.violox.tentag.controllers;

import com.violox.tentag.domain.*;
import com.violox.tentag.entity.*;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Named(value = "indexController")
@RequestScoped
public class IndexController {

    Contact contact;
    @Inject
    private ContactRelation contactRepo;

    @PostConstruct
    public void init() {
        if (contact == null) {
            
            /*
             * Test to pull a single contact out of the database. Contact
             * Contact 3 is albert einstein
             * Key interface is used to handle concatinated keys
             */
            Key key = new SingleIntegerKey();
            key.setKey(3);
            contact = contactRepo.get(key);
        }
    }

    public String getLastname() {
        return contact.getLastName();
    }

    public void setLastname(String lastname) {
        //placeholder
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        //placeholder
    }

    public Integer getId() {
        return contact.getId();
    }

    public void setId(Integer id) {
        //placeholder
    }

}

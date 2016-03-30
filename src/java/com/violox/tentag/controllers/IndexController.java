package com.violox.tentag.controllers;

import com.violox.tentag.domain.*;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "indexController")
@RequestScoped
public class IndexController {

    @Inject
    private DbContext dbcontext;
    @Inject
    private Key<Integer> contact_key;

    Contact contact;
    
    @PostConstruct
    public void init() {
        if (contact == null) {
            contact_key.setKey(3);
            contact = (Contact) dbcontext.Contact().get(contact_key);
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

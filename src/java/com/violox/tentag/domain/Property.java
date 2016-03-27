package com.violox.tentag.domain;

import java.util.ArrayList;

/**
 * 
 */
public class Property {

    /**
     * Default constructor
     */
    public Property() {
    }

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Contact contact;

    /**
     * 
     */
    private Address mailingAddress;

    /**
     * 
     */
    private Address billingAddress;

    /**
     * 
     */
    private ArrayList<Group> groups;

}
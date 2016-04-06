/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.violox.tentag.domain;

import javax.validation.constraints.NotNull;

/**
 *
 * @author gordon
 */
public class Address {
    
    protected Integer id;
    @NotNull(message = "Must have address line 1.")
    protected String addressLine1;
    protected String addressLine2;
    @NotNull(message = "Must have a city.")
    protected String city;
    @NotNull(message = "Must select a state.")
    protected State state;
    @NotNull(message = "Must have zip code.")
    protected String zip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
}

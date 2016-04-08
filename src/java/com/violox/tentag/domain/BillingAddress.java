package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;


@RequestScoped
public class BillingAddress extends Address {

    @Override
    public void fillState(DbContext context) {
        this.state = (State) context.StateBillingAddress().getByChild(this);
    }

    
}

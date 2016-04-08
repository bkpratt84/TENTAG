package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;


@RequestScoped
public class MailingAddress extends Address {

    @Override
    public void fillState(DbContext context) {
        this.state = (State) context.StateMailingAddress().getByChild(this);
    }

}

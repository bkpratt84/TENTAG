package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StateMailingAddressRelationship implements Relationship<MailingAddress, State> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private StateRelation parent;
    @Inject
    private MailingAddressRelation child;

    @Override
    public ArrayList<MailingAddress> getByParent(State parent) {
        return child.getByStateMailing(parent);
    }

    @Override
    public State getByChild(MailingAddress child) {
        child_key.setKey(child.getState().getId());
        return parent.get(child_key);
    }

}

package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailingAddressPropertyRelationship implements Relationship<Property, MailingAddress> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private MailingAddressRelation parent;
    @Inject
    private PropertyRelation child;

    @Override
    public ArrayList<Property> getByParent(MailingAddress parent) {
        return child.getByMailingAddress(parent);
    }

    @Override
    public MailingAddress getByChild(Property child) {
        child_key.setKey(child.getMailingAddress().getId());
        return parent.get(child_key);
    }

}

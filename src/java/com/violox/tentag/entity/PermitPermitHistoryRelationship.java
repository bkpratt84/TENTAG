package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PermitPermitHistoryRelationship implements Relationship<PermitHistory, Permit> {

    public PermitPermitHistoryRelationship() {
    }

    @Override
    public ArrayList<PermitHistory> getByParent(Permit parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Permit getByChild(PermitHistory child) {
        // TODO implement here
        return null;
    }

}

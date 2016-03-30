package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BatchPermitRelationship implements Relationship<Permit, Batch> {

    public BatchPermitRelationship() {
    }

    @Override
    public ArrayList<Permit> getByParent(Batch parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Batch getByChild(Permit child) {
        // TODO implement here
        return null;
    }

}

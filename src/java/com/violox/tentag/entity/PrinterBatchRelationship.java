package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PrinterBatchRelationship implements Relationship<Batch, Printer> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private PrinterRelation parent;
    @Inject
    private BatchRelation child;

    @Override
    public ArrayList<Batch> getByParent(Printer parent) {
        return child.getByPrinter(parent);
    }

    @Override
    public Printer getByChild(Batch child) {
        child_key.setKey(child.getPrinterId());
        return parent.get(child_key);
    }

}

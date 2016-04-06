package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PrinterGroupPrinterRelationship implements Relationship<GroupPrinter, Printer> {

    @Inject
    private Key<Integer> child_key;
    @Inject
    private PrinterRelation parent;
    @Inject
    private GroupPrinterRelation child;

    @Override
    public ArrayList<GroupPrinter> getByParent(Printer parent) {
        return child.getbyPrinter(parent);
    }

    @Override
    public Printer getByChild(GroupPrinter child) {
        child_key.setKey(child.getPrinter().getId());
        return parent.get(child_key);
    }

}

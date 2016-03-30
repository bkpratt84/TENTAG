package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PrinterGroupPrinterRelationship implements Relationship<GroupPrinter, Printer> {

    public PrinterGroupPrinterRelationship() {
    }

    @Override
    public ArrayList<GroupPrinter> getByParent(Printer parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Printer getByChild(GroupPrinter child) {
        // TODO implement here
        return null;
    }

}

package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PrinterBatchRelationship implements Relationship<Batch, Printer> {

    public PrinterBatchRelationship() {
    }

    @Override
    public ArrayList<Batch> getByParent(Printer parent) {
        // TODO implement here
        return null;
    }

    @Override
    public Printer getByChild(Batch child) {
        // TODO implement here
        return null;
    }

}

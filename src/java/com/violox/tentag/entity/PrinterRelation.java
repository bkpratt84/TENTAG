package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PrinterRelation implements Relation<Printer, Integer> {

    public PrinterRelation() {
    }

    @Override
    public Printer post(Printer item) {
        // TODO implement here
        return null;
    }

    @Override
    public Printer get(Key<Integer> key) {
        // TODO implement here
        return null;
    }

    @Override
    public ArrayList<Printer> get() {
        // TODO implement here
        return null;
    }

    @Override
    public void put(Printer item) {
        // TODO implement here
    }

    @Override
    public void delete(Printer item) {
        // TODO implement here
    }

    @Override
    public Integer getKey() {
        // TODO implement here
        return null;
    }

    @Override
    public void setKey(Integer key) {
        // TODO implement here
    }

}

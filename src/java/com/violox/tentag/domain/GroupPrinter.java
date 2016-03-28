package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GroupPrinter {

    private Group group;
    private Printer printer;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

}

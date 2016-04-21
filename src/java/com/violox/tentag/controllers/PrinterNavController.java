package com.violox.tentag.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "printerNavController")
@ViewScoped
public class PrinterNavController implements Serializable {    
    private String page;
    
    @Inject
    NavigationController nav;
    
    @PostConstruct
    public void init() {
        if (page == null) {
            page = nav.toPrinterHome(false, false);
        }
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
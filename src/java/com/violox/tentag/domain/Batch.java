package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class Batch {

    private Integer id;
    @NotNull(message = "Must have a valid property.")
    private Property property;
    private Printer printer;
    @NotNull(message = "Must have a valid Status.")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

}
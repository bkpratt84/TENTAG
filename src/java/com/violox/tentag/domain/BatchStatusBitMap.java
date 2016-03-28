package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class BatchStatusBitMap {

    private Integer bit;
    private String value;

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    

}
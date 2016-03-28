package com.violox.tentag.domain;

import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.*;

@RequestScoped
public class State {

    private Integer id;
    @NotNull(message = "State name must not be blank.")
    private String name;
    @NotNull(message = "State abbr must not be blank.")
    private String abbr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

}

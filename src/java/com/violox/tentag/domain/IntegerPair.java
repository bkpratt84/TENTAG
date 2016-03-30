package com.violox.tentag.domain;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IntegerPair {

    private Integer first;

    private Integer second;

    public Integer getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

}

package com.violox.tentag.entity;

public class SingleIntegerKey implements Key<Integer> {

    private Integer key;

    public SingleIntegerKey() {
    }

    @Override
    public Integer getKey() {

        return key;
    }

    @Override
    public void setKey(Integer key) {
        this.key = key;
    }

}

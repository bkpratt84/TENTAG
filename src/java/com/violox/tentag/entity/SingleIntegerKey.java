package com.violox.tentag.entity;

import com.violox.tentag.domain.Key;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
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

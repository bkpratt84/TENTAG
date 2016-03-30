package com.violox.tentag.entity;

import com.violox.tentag.domain.IntegerPair;
import com.violox.tentag.domain.Key;

public class DoubleIntegerKey implements Key<IntegerPair> {

    private IntegerPair key;

    public DoubleIntegerKey() {
    }

    @Override
    public IntegerPair getKey() {
        return key;
    }

    @Override
    public void setKey(IntegerPair key) {
        this.key = key;
    }

}

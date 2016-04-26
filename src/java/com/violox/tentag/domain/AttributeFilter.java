package com.violox.tentag.domain;

import java.util.ArrayList;

public interface AttributeFilter<T, U> {

    public ArrayList<T> getByAttribute(String column, U value);

}

package com.violox.tentag.entity;

import java.util.*;

public interface Relationship<C, P> {

    public ArrayList<C> getByParent(P parent);

    public P getByChild(C child);

}

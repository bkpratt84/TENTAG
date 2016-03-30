package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface Relationship<C, P> {

    public ArrayList<C> getByParent(P parent);

    public P getByChild(C child);

}

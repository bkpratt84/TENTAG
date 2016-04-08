package com.violox.tentag.domain;

import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface Relation<T, U> {

    public T post(T item);

    public T get(Key<U> key);

    public T getByAlternateKey(T item);

    public ArrayList<T> get();

    public void put(T item);

    public void delete(T item);

    public U getKey();

    public void setKey(U key);

}

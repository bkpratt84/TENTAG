package com.violox.tentag.entity;

import java.util.ArrayList;

public interface Relation<T, U> {

    public T post(T item);

    public T get(Key<U> key);

    public ArrayList<T> get();

    public void put(T item);

    public void delete(T item);

    public U getKey();

    public void setKey(U key);

}

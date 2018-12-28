package edu.core.java.print.repository;

import edu.core.java.print.vo.ValueObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Repository<V extends ValueObject> {

    List<V> collection = new ArrayList<>();

    // Public API

    public List<V> findAll() { return collection; }

    public List<V> findBy(String type) {
        return Collections.emptyList();
    }

    public abstract void add(V object);
    public abstract void update(V object);
    public abstract void remove(long id);

}

package edu.core.java.rabbitbag.repository;

import edu.core.java.rabbitbag.vo.ValueObject;

import java.util.Collections;
import java.util.List;

public abstract class Repository<V extends ValueObject> {

    protected List<V> collection;

    List<V> findAll() {
        return collection;
    }

    List<V> findBy(String type) {
        return Collections.emptyList();
    }

    abstract void add(V object);
    abstract void update(V object);
    abstract void remove(long id);

}

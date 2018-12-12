package edu.core.java.rabbitbag.repository;

import edu.core.java.rabbitbag.vo.ValueObject;

import java.util.Collections;
import java.util.List;

public class Repository<V extends ValueObject> {

    V find(long id) {
        return;
    }

    List<V> findAll() {
        return Collections.emptyList();
    }

    List<V> findBy(String type) {
        return Collections.emptyList();
    }

    void add(V object) {

    }


    void update(V object) {

    }


    void remove(long id) {

    }

}

package edu.core.java.rabbitbag.repository;

import edu.core.java.rabbitbag.vo.KitsValueObject;

public class KitsRepository extends Repository<KitsValueObject> {

    @Override
    void add(KitsValueObject object) {
        collection.add(object);
    }

    @Override
    void update(KitsValueObject object) {
        int index = collection.indexOf(object);
        collection.set(index, object);
        // todo update json in file
    }

    @Override
    void remove(long id) {

    }
}

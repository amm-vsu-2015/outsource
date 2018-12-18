package edu.core.java.rabbitbag.repository;

import edu.core.java.rabbitbag.vo.KitsValueObject;

public class KitsRepository extends Repository<KitsValueObject> {

    @Override
    public void add(KitsValueObject object) {
        collection.add(object);
    }

    @Override
    public void update(KitsValueObject object) {
        int index = collection.indexOf(object);
        collection.set(index, object);
        // todo update json in file
    }

    @Override
    public void remove(long id) {
        collection.removeIf(kit -> kit.getId() == id);
    }

}

package edu.core.java.print.repository;

import edu.core.java.print.vo.KitsValueObject;

public class KitsRepository extends Repository<KitsValueObject> {

    @Override
    public void add(KitsValueObject object) {
        collection.add(object);
    }

    @Override
    public void update(KitsValueObject object) {
        for (int idx_i = 0; idx_i < collection.size(); idx_i++) {
            if (object.getId() == collection.get(idx_i).getId()) {
                collection.set(idx_i, object);
                break;
            }
        }
    }

    @Override
    public void remove(long id) {
        collection.removeIf(kit -> kit.getId() == id);
    }

}

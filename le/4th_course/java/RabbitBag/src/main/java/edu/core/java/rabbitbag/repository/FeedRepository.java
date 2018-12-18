package edu.core.java.rabbitbag.repository;

import edu.core.java.rabbitbag.vo.FeedValueObject;
import edu.core.java.rabbitbag.vo.KitsValueObject;

public class FeedRepository extends Repository<FeedValueObject> {

    @Override
    public void add(FeedValueObject object) {
        collection.add(object);
    }

    @Override
    public void update(FeedValueObject object) {
        int index = collection.indexOf(object);
        collection.set(index, object);
        // todo reuse
    }

    @Override
    public void remove(long id) {

    }

}

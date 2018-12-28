package edu.core.java.print.repository;

import edu.core.java.print.vo.FeedValueObject;

public class FeedRepository extends Repository<FeedValueObject> {

    @Override
    public void add(FeedValueObject object) {
        collection.add(object);
    }

    @Override
    public void update(FeedValueObject object) {
        for (int idx_i = 0; idx_i < collection.size(); idx_i++) {
            if (object.getId() == collection.get(idx_i).getId()) {
                collection.set(idx_i, object);
                break;
            }
        }
    }

    @Override
    public void remove(long id) {
        collection.removeIf(feed -> feed.getId() == id);
    }

}

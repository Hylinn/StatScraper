package io.github.hylinn.saver;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractEntitySaver<T, C> implements Saver<T, C> {

    protected abstract Collection<C> getCollection();
    protected abstract void clearCollection();
    protected abstract Object saveEntity(T t, C c);

    @Override
    public Saver<T, C> add(C c) {
        getCollection().add(c);
        return this;
    }

    @Override
    public Saver<T, C> reset() {
        clearCollection();
        return this;
    }

    @Override
    public Collection save(T t) {
        Collection<C> collection = getCollection();
        Collection objects = new ArrayList<>(collection.size());

        for (C c : collection) {
            objects.add(saveEntity(t, c));
        }

        return objects;
    }
}

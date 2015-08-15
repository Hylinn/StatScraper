package io.github.hylinn.saver;

import java.util.Collection;

public interface Saver<T, C> {

    Saver<T, C> add(C c);
    Saver<T, C> reset();
    Collection save(T t);
}

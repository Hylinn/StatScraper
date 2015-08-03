package io.github.hylinn.statistics.hibernate;

import java.io.Serializable;
import java.util.Collection;

public interface Service<T, Id extends Serializable> {

    void save(T entity);
    void update(T entity);
    T findById(Id id);
    void delete(T entity);
    Collection<T> findAll();
    void deleteAll();
}

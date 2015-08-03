package io.github.hylinn.statistics.hibernate;

import java.util.Collection;

public interface DAO<T, I> {

    void save(T entity);
    void update(T entity);
    T findById(I id);
    void delete(T entity);
    Collection<T> findAll();
    void deleteAll();
}

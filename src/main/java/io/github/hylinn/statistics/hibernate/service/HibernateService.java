package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

@org.springframework.stereotype.Service
public abstract class HibernateService<T, Id extends Serializable> implements Service<T, Id> {

    protected abstract DAO<T, Id> getDAO();

    @Transactional
    public void save(T entity) {
        getDAO().save(entity);
    }

    @Transactional
    public void update(T entity) {
        getDAO().update(entity);
    }

    @Transactional
    public T findById(Id id) {
        return getDAO().findById(id);
    }

    @Transactional
    public void delete(T entity) {
        getDAO().delete(entity);
    }

    @Transactional
    public Collection<T> findAll() {
        return getDAO().findAll();
    }

    @Transactional
    public void deleteAll() {
        getDAO().deleteAll();
    }
}

package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.DAO;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.Collection;

public abstract class HibernateDAO<T, Id extends Serializable> implements DAO<T, Id> {

    protected abstract SessionFactory getSessionFactory();
    protected abstract Class getEntityClass();
    protected abstract void initialize(T t);

    public void save(T entity) {
        getSessionFactory().getCurrentSession().save(entity);
    }

    public void update(T entity) {
        getSessionFactory().getCurrentSession().update(entity);
    }

    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    public Collection<T> findAll() {
        return getSessionFactory().getCurrentSession().createCriteria(getEntityClass()).list();
    }

    public T findById(Id id) {
        T t = (T) getSessionFactory().getCurrentSession().get(getEntityClass(), id);
        initialize(t);
        return t;
    }

    public void deleteAll() {
        Collection<T> entities = findAll();
        for (T entity : entities) {
            delete(entity);
        }
    }
}

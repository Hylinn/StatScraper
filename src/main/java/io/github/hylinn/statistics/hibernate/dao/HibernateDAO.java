package io.github.hylinn.statistics.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.Collection;

public abstract class HibernateDAO<T, Id extends Serializable> implements DAO<T, Id> {

    protected abstract SessionFactory getSessionFactory();
    protected abstract Class getEntityClass();
    protected abstract void initialize(T t);
    protected abstract T find(T entity);

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

    public T findByEntity(T entity) {
        Session session = getSessionFactory().getCurrentSession();
        String entityName = session.getEntityName(entity);
        Serializable id = session.getIdentifier(entity);
        T t = (T) getSessionFactory().getCurrentSession().get(entityName, id);

        return t;
    }

    public T findById(Id id) {
        T t = (T) getSessionFactory().getCurrentSession().get(getEntityClass(), id);
        if (t != null) initialize(t);
        return t;
    }

    public void deleteAll() {
        Collection<T> entities = findAll();
        for (T entity : entities) {
            delete(entity);
        }
    }

    public T saveOrFind(T entity) {
        T t = find(entity);
        if (t == null) {
            t = entity;
            save(t);
        }
        else
            update(t);

        return t;
    }
}

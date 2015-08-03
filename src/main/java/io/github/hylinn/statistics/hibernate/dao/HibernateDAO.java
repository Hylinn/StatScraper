package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.DAO;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class HibernateDAO<T, Id extends Serializable> implements DAO<T, Id> {

    protected abstract SessionFactory getSessionFactory();
    protected abstract String getTableName();
    public abstract T findById(Id id);

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
        Collection<T> entities = (Collection<T>) getSessionFactory().getCurrentSession().createQuery("from " + getTableName()).list();
        return entities;
    }

    public void deleteAll() {
        Collection<T> entities = findAll();
        for (T entity : entities) {
            delete(entity);
        }
    }
}

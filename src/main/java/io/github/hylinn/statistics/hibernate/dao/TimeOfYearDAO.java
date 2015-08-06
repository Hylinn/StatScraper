package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.TimeOfYear;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TimeOfYearDAO extends HibernateDAO<TimeOfYear, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return TimeOfYear.class; }

    @Override
    protected void initialize(TimeOfYear timeOfYear) {}
}

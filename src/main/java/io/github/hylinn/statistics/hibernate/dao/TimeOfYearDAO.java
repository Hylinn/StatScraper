package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.TimeOfYear;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TimeOfYearDAO extends HibernateDAO<TimeOfYear, String> {

    private static final String TABLE_NAME = "time_of_year";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public TimeOfYear findById(String id) {
        return (TimeOfYear) getSessionFactory().getCurrentSession().load(TimeOfYear.class, id);
    }
}

package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.SkaterStatistics;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SkaterStatisticsDAO extends HibernateDAO<SkaterStatistics, Integer> {

    private static final String TABLE_NAME = "skater_statistics";

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
    public SkaterStatistics findById(Integer id) {
        return (SkaterStatistics) getSessionFactory().getCurrentSession().load(SkaterStatistics.class, id);
    }
}

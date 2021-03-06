package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.SkaterStatistics;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SkaterStatisticsDAO extends HibernateDAO<SkaterStatistics, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return SkaterStatistics.class; }

    @Override
    protected void initialize(SkaterStatistics skaterStatistics) {}

    @Override
    protected SkaterStatistics find(SkaterStatistics entity) {
        return findById(entity.getId());
    }
}

package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.GoalieStatistics;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoalieStatisticsDAO extends HibernateDAO<GoalieStatistics, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return GoalieStatistics.class; }

    @Override
    protected void initialize(GoalieStatistics goalieStatistics) {}
}

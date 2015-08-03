package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.GoalieStatistics;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoalieStatisticsDAO extends HibernateDAO<GoalieStatistics, Integer> {

    private static final String TABLE_NAME = "goalie_statistics";

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
    public GoalieStatistics findById(Integer id) {
        return (GoalieStatistics) getSessionFactory().getCurrentSession().load(GoalieStatistics.class, id);
    }
}

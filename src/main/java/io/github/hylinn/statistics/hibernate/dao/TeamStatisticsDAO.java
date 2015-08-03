package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.TeamStatistics;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamStatisticsDAO extends HibernateDAO<TeamStatistics, Integer> {

    private static final String TABLE_NAME = "team_statistics";

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
    public TeamStatistics findById(Integer id) {
        return (TeamStatistics) getSessionFactory().getCurrentSession().load(TeamStatistics.class, id);
    }
}

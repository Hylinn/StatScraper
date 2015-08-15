package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.TeamStatistics;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamStatisticsDAO extends HibernateDAO<TeamStatistics, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return TeamStatistics.class; }

    @Override
    protected void initialize(TeamStatistics teamStatistics) {}

    @Override
    protected TeamStatistics find(TeamStatistics entity) {
        return findById(entity.getId());
    }
}

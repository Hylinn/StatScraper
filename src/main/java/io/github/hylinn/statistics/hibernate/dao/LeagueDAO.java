package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.League;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeagueDAO extends HibernateDAO<League, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return League.class; }

    @Override
    protected void initialize(League league) {
        Hibernate.initialize(league.getLeagueSeasons());
    }
}

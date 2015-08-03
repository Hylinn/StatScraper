package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeagueSeasonDAO extends HibernateDAO<LeagueSeason, Integer> {

    private static final String TABLE_NAME = "league_season";

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
    public LeagueSeason findById(Integer id) {
        return (LeagueSeason) getSessionFactory().getCurrentSession().load(LeagueSeason.class, id);
    }
}

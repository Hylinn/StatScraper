package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeagueSeasonDivisionDAO extends HibernateDAO<LeagueSeasonDivision, Integer> {

    private static final String TABLE_NAME = "league_season_division";

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
    public LeagueSeasonDivision findById(Integer id) {
        return (LeagueSeasonDivision) getSessionFactory().getCurrentSession().load(LeagueSeasonDivision.class, id);
    }
}

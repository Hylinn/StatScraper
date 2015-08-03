package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.DivisionTeamPlayer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DivisionTeamPlayerDAO extends HibernateDAO<DivisionTeamPlayer, Integer> {

    private static final String TABLE_NAME = "division_team_player";

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
    public DivisionTeamPlayer findById(Integer id) {
        return (DivisionTeamPlayer) getSessionFactory().getCurrentSession().load(DivisionTeamPlayer.class, id);
    }
}

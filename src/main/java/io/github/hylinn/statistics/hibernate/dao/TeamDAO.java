package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.Team;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAO extends HibernateDAO<Team, Integer> {

    private static final String TABLE_NAME = "team";

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
    public Team findById(Integer id) {
        return (Team) getSessionFactory().getCurrentSession().load(Team.class, id);
    }
}

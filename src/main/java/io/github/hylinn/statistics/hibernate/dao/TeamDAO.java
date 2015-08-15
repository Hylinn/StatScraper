package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.Team;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAO extends HibernateDAO<Team, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return Team.class; }

    @Override
    protected void initialize(Team team) {
        Hibernate.initialize(team.getDivisionTeams());
    }

    @Override
    protected Team find(Team entity) {
        return findById(entity.getId());
    }
}

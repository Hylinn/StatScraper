package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.Division;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DivisionDAO extends HibernateDAO<Division, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return Division.class; }

    @Override
    protected void initialize(Division division) {
        Hibernate.initialize(division.getLeagueSeasonTeams());
    }

    @Override
    protected Division find(Division entity) {
        return findById(entity.getId());
    }
}

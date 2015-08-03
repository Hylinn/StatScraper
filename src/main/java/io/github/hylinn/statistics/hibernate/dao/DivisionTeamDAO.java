package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DivisionTeamDAO extends HibernateDAO<DivisionTeam, Integer> {

    private static final String TABLE_NAME = "division_team";

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
    public DivisionTeam findById(Integer id) {
        return (DivisionTeam) getSessionFactory().getCurrentSession().load(DivisionTeam.class, id);
    }
}

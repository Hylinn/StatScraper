package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.Player;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAO extends HibernateDAO<Player, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return Player.class; }

    @Override
    protected void initialize(Player player) {
        Hibernate.initialize(player.getDivisionTeamPlayers());
    }

    @Override
    protected Player find(Player entity) {
        return findById(entity.getName());
    }
}

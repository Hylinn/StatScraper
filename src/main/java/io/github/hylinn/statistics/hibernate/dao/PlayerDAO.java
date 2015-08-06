package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.Player;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerDAO extends HibernateDAO<Player, Integer> {

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

    public Player findByUnique(String name) {
        List<Player> players = getSessionFactory().getCurrentSession().createCriteria(getEntityClass())
            .add(
                Restrictions.eq("name", name))
            .list();

        if (players.size() == 0)
            return null;
        else {
            Player player = players.get(0);
            initialize(player);
            return player;
        }
    }
}

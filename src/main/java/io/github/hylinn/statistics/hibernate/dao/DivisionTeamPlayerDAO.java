package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DivisionTeamPlayerDAO extends HibernateDAO<DivisionTeamPlayer, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return DivisionTeamPlayer.class; }

    public DivisionTeamPlayer findByUnique(DivisionTeam divisionTeam, Player player) {
        List<DivisionTeamPlayer> divisionTeamPlayers = getSessionFactory().getCurrentSession().createCriteria(getEntityClass())
            .add(
                Restrictions.and(
                    Restrictions.eq("divisionTeam", divisionTeam),
                    Restrictions.eq("player", player)))
            .list();

        if (divisionTeamPlayers.size() == 0)
            return null;
        else
            return divisionTeamPlayers.get(0);
    }
}

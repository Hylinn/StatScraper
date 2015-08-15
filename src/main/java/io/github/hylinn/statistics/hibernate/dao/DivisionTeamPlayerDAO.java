package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeamPlayer;
import io.github.hylinn.statistics.hibernate.entity.Player;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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

    @Override
    protected void initialize(DivisionTeamPlayer divisionTeamPlayer) {}

    @Override
    protected DivisionTeamPlayer find(DivisionTeamPlayer entity) {
        return findByUnique(entity.getDivisionTeam(), entity.getPlayer());
    }

    public DivisionTeamPlayer findByUnique(DivisionTeam divisionTeam, Player player) {
        List<DivisionTeamPlayer> divisionTeamPlayers = getSessionFactory().getCurrentSession().createCriteria(getEntityClass())
            .add(
                Restrictions.and(
                    Restrictions.eq("divisionTeam", divisionTeam),
                    Restrictions.eq("player", player)))
            .list();

        if (divisionTeamPlayers.size() == 0)
            return null;
        else {
            DivisionTeamPlayer divisionTeamPlayer = divisionTeamPlayers.get(0);
            initialize(divisionTeamPlayer);
            return divisionTeamPlayer;
        }
    }

    public Collection<DivisionTeamPlayer> findByPlayer(Player player) {
        List<DivisionTeamPlayer> divisionTeamPlayers = getSessionFactory().getCurrentSession().createCriteria(getEntityClass())
            .add(
                Restrictions.eq("player", player))
            .list();

        for (DivisionTeamPlayer divisionTeamPlayer : divisionTeamPlayers)
            initialize(divisionTeamPlayer);

        return divisionTeamPlayers;
    }
}

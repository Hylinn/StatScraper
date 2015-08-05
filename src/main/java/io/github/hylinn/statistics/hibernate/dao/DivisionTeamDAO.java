package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DivisionTeamDAO extends HibernateDAO<DivisionTeam, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return DivisionTeam.class; }

    public DivisionTeam findByUnique(LeagueSeasonDivision leagueSeasonDivision, Team team) {
        List<DivisionTeam> divisionTeams = getSessionFactory().getCurrentSession().createCriteria(getEntityClass())
            .add(
                    Restrictions.and(
                            Restrictions.eq("leagueSeasonDivision", leagueSeasonDivision),
                            Restrictions.eq("team", team)))
            .list();

        if (divisionTeams.size() == 0)
            return null;
        else
            return divisionTeams.get(0);
    }
}

package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import io.github.hylinn.statistics.hibernate.entity.Team;
import org.hibernate.Hibernate;
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

    @Override
    protected void initialize(DivisionTeam divisionTeam) {
        Hibernate.initialize(divisionTeam.getDivisionTeamPlayers());
    }

    @Override
    protected DivisionTeam find(DivisionTeam entity) {
        return findByUnique(entity.getLeagueSeasonDivision(), entity.getTeam());
    }

    public DivisionTeam findByUnique(LeagueSeasonDivision leagueSeasonDivision, Team team) {
        List<DivisionTeam> divisionTeams = getSessionFactory().getCurrentSession().createCriteria(getEntityClass())
            .add(
                    Restrictions.and(
                            Restrictions.eq("leagueSeasonDivision", leagueSeasonDivision),
                            Restrictions.eq("team", team)))
            .list();

        if (divisionTeams.size() == 0)
            return null;
        else {
            DivisionTeam divisionTeam = divisionTeams.get(0);
            initialize(divisionTeam);
            return divisionTeam;
        }
    }
}

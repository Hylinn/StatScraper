package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.Division;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeagueSeasonDivisionDAO extends HibernateDAO<LeagueSeasonDivision, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return LeagueSeasonDivision.class; }

    public LeagueSeasonDivision findByUnique(LeagueSeason leagueSeason, Division division) {
        List<LeagueSeasonDivision> leagueSeasonDivisions = getSessionFactory().getCurrentSession().createCriteria(getEntityClass())
            .add(
                Restrictions.and(
                    Restrictions.eq("leagueSeason", leagueSeason),
                    Restrictions.eq("division", division)))
            .list();

        if (leagueSeasonDivisions.size() == 0)
            return null;
        else
            return leagueSeasonDivisions.get(0);
    }
}

package io.github.hylinn.statistics.hibernate.dao;

import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.entity.Season;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class LeagueSeasonDAO extends HibernateDAO<LeagueSeason, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    protected Class getEntityClass() { return LeagueSeason.class; }

    public LeagueSeason findByUnique(League league, Season season) {
        List<LeagueSeason> leagueSeasons = getSessionFactory().getCurrentSession().createCriteria(getEntityClass())
            .add(
                Restrictions.and(
                    Restrictions.eq("league", league),
                    Restrictions.eq("season", season)))
            .list();

        if (leagueSeasons.size() == 0)
            return null;
        else
            return leagueSeasons.get(0);
    }
}

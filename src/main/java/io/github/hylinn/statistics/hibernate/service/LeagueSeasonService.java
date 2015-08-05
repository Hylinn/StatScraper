package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.LeagueSeasonDAO;
import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.entity.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeagueSeasonService extends HibernateService<LeagueSeason, Integer> {

    @Autowired
    private LeagueSeasonDAO dao;

    @Override
    protected DAO<LeagueSeason, Integer> getDAO() {
        return dao;
    }

    @Transactional
    public LeagueSeason findByUnique(League league, Season season) {
        return dao.findByUnique(league, season);
    }
}
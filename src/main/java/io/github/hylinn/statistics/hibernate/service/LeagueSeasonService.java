package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.LeagueSeasonDAO;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class LeagueSeasonService extends HibernateService<LeagueSeason, Integer> {

    @Autowired
    private LeagueSeasonDAO dao;

    @Override
    protected DAO<LeagueSeason, Integer> getDAO() {
        return dao;
    }
}
package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.dao.DAO;
import io.github.hylinn.statistics.hibernate.dao.LeagueSeasonDivisionDAO;
import io.github.hylinn.statistics.hibernate.entity.Division;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeagueSeasonDivisionService extends HibernateService<LeagueSeasonDivision, Integer> {

    @Autowired
    private LeagueSeasonDivisionDAO dao;

    @Override
    protected DAO<LeagueSeasonDivision, Integer> getDAO() {
        return dao;
    }

    @Transactional
    public LeagueSeasonDivision findByUnique(LeagueSeason leagueSeason, Division division) { return dao.findByUnique(leagueSeason, division); }
}

package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.LeagueSeasonDivisionDAO;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class LeagueSeasonDivisionService extends HibernateService<LeagueSeasonDivision, Integer> {

    @Autowired
    private LeagueSeasonDivisionDAO dao;

    @Override
    protected DAO<LeagueSeasonDivision, Integer> getDAO() {
        return dao;
    }
}

package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.dao.DAO;
import io.github.hylinn.statistics.hibernate.dao.LeagueDAO;
import io.github.hylinn.statistics.hibernate.entity.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService extends HibernateService<League, Integer> {

    @Autowired
    private LeagueDAO dao;

    @Override
    protected DAO<League, Integer> getDAO() {
        return dao;
    }
}

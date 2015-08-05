package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.SeasonDAO;
import io.github.hylinn.statistics.hibernate.entity.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonService extends HibernateService<Season, Integer> {

    @Autowired
    private SeasonDAO dao;

    @Override
    protected DAO<Season, Integer> getDAO() {
        return dao;
    }
}

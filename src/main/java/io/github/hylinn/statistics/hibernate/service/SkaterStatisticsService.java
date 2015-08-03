package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.SkaterStatisticsDAO;
import io.github.hylinn.statistics.hibernate.entity.SkaterStatistics;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class SkaterStatisticsService extends HibernateService<SkaterStatistics, Integer> {

    @Autowired
    private SkaterStatisticsDAO dao;

    @Override
    protected DAO<SkaterStatistics, Integer> getDAO() {
        return dao;
    }
}

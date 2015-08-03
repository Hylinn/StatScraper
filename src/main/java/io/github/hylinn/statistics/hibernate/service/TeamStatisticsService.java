package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.TeamStatisticsDAO;
import io.github.hylinn.statistics.hibernate.entity.TeamStatistics;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TeamStatisticsService extends HibernateService<TeamStatistics, Integer> {

    @Autowired
    private TeamStatisticsDAO dao;

    @Override
    protected DAO<TeamStatistics, Integer> getDAO() {
        return dao;
    }
}

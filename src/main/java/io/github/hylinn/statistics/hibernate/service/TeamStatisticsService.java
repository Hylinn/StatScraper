package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.dao.DAO;
import io.github.hylinn.statistics.hibernate.dao.TeamStatisticsDAO;
import io.github.hylinn.statistics.hibernate.entity.TeamStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamStatisticsService extends HibernateService<TeamStatistics, Integer> {

    @Autowired
    private TeamStatisticsDAO dao;

    @Override
    protected DAO<TeamStatistics, Integer> getDAO() {
        return dao;
    }
}

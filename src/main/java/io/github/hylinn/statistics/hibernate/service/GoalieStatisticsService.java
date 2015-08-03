package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.GoalieStatisticsDAO;
import io.github.hylinn.statistics.hibernate.entity.GoalieStatistics;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class GoalieStatisticsService extends HibernateService<GoalieStatistics, Integer> {

    @Autowired
    private GoalieStatisticsDAO dao;

    @Override
    protected DAO<GoalieStatistics, Integer> getDAO() {
        return dao;
    }
}

package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.dao.DAO;
import io.github.hylinn.statistics.hibernate.dao.GoalieStatisticsDAO;
import io.github.hylinn.statistics.hibernate.entity.GoalieStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalieStatisticsService extends HibernateService<GoalieStatistics, Integer> {

    @Autowired
    private GoalieStatisticsDAO dao;

    @Override
    protected DAO<GoalieStatistics, Integer> getDAO() {
        return dao;
    }
}

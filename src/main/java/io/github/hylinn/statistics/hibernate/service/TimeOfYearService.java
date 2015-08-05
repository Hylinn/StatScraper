package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.TimeOfYearDAO;
import io.github.hylinn.statistics.hibernate.entity.TimeOfYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeOfYearService extends HibernateService<TimeOfYear, String> {

    @Autowired
    private TimeOfYearDAO dao;

    @Override
    protected DAO<TimeOfYear, String> getDAO() {
        return dao;
    }
}
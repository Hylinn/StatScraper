package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.DivisionDAO;
import io.github.hylinn.statistics.hibernate.entity.Division;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class DivisionService extends HibernateService<Division, Integer> {

    @Autowired
    private DivisionDAO dao;

    @Override
    protected DAO<Division, Integer> getDAO() {
        return dao;
    }
}

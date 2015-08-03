package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.DivisionTeamDAO;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class DivisionTeamService extends HibernateService<DivisionTeam, Integer> {

    @Autowired
    private DivisionTeamDAO dao;

    @Override
    protected DAO<DivisionTeam, Integer> getDAO() {
        return dao;
    }
}

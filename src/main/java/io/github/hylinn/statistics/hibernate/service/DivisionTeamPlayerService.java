package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.DivisionTeamPlayerDAO;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeamPlayer;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class DivisionTeamPlayerService extends HibernateService<DivisionTeamPlayer, Integer> {

    @Autowired
    private DivisionTeamPlayerDAO dao;

    @Override
    protected DAO<DivisionTeamPlayer, Integer> getDAO() {
        return dao;
    }
}
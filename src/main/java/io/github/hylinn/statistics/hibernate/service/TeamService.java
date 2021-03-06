package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.dao.DAO;
import io.github.hylinn.statistics.hibernate.dao.TeamDAO;
import io.github.hylinn.statistics.hibernate.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends HibernateService<Team, Integer> {

    @Autowired
    private TeamDAO dao;

    @Override
    protected DAO<Team, Integer> getDAO() {
        return dao;
    }
}

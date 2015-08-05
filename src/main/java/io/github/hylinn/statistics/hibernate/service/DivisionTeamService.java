package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.DivisionTeamDAO;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import io.github.hylinn.statistics.hibernate.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DivisionTeamService extends HibernateService<DivisionTeam, Integer> {

    @Autowired
    private DivisionTeamDAO dao;

    @Override
    protected DAO<DivisionTeam, Integer> getDAO() {
        return dao;
    }

    @Transactional
    public DivisionTeam findByUnique(LeagueSeasonDivision leagueSeasonDivision, Team team) { return dao.findByUnique(leagueSeasonDivision, team); }
}

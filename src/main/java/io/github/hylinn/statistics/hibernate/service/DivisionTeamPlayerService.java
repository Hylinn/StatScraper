package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.dao.DAO;
import io.github.hylinn.statistics.hibernate.dao.DivisionTeamPlayerDAO;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeamPlayer;
import io.github.hylinn.statistics.hibernate.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class DivisionTeamPlayerService extends HibernateService<DivisionTeamPlayer, Integer> {

    @Autowired
    private DivisionTeamPlayerDAO dao;

    @Override
    protected DAO<DivisionTeamPlayer, Integer> getDAO() {
        return dao;
    }

    @Transactional
    public DivisionTeamPlayer findByUnique(DivisionTeam divisionTeam, Player player) { return dao.findByUnique(divisionTeam, player); }

    @Transactional
    public Collection<DivisionTeamPlayer> findByPlayer(Player player) {
        return dao.findByPlayer(player);
    }
}
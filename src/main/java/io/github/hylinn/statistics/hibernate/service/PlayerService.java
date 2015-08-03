package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.PlayerDAO;
import io.github.hylinn.statistics.hibernate.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class PlayerService extends HibernateService<Player, Integer> {

    @Autowired
    private PlayerDAO dao;

    @Override
    protected DAO<Player, Integer> getDAO() {
        return dao;
    }
}

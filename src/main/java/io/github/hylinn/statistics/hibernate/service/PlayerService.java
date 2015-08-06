package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.PlayerDAO;
import io.github.hylinn.statistics.hibernate.entity.Player;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService extends HibernateService<Player, Integer> {

    @Autowired
    private PlayerDAO dao;

    @Override
    protected DAO<Player, Integer> getDAO() {
        return dao;
    }

    @Transactional
    public Player findByUnique(String name) { return dao.findByUnique(name); }
}

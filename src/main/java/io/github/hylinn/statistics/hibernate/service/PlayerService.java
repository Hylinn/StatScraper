package io.github.hylinn.statistics.hibernate.service;

import io.github.hylinn.statistics.hibernate.dao.DAO;
import io.github.hylinn.statistics.hibernate.dao.PlayerDAO;
import io.github.hylinn.statistics.hibernate.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends HibernateService<Player, String> {

    @Autowired
    private PlayerDAO dao;

    @Override
    protected DAO<Player, String> getDAO() {
        return dao;
    }

}

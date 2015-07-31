package io.github.hylinn.dao.statistics;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerDAOImpl implements PlayerDAO {

    @Autowired
    private SessionFactory sessionFactory;
}

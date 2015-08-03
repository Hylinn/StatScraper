package io.github.hylinn.statistics.spring.controller;

import io.github.hylinn.statistics.hibernate.DAO;
import io.github.hylinn.statistics.hibernate.dao.PlayerDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @RequestMapping("/player")
    public DAO player(@RequestParam(value="id", required = true) int id) {
        return new PlayerDAO();
    }
}

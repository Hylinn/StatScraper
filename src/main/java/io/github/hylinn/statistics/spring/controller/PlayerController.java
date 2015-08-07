package io.github.hylinn.statistics.spring.controller;

import io.github.hylinn.statistics.hibernate.entity.Player;
import io.github.hylinn.statistics.hibernate.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping("/player")
    public Player player(@RequestParam(value="id", required = true) int id) {
        Player player = playerService.findById(id);
        return player;
    }
}

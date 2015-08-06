package io.github.hylinn.statistics.spring.controller;

import io.github.hylinn.statistics.hibernate.entity.DivisionTeamPlayer;
import io.github.hylinn.statistics.hibernate.entity.SkaterStatistics;
import io.github.hylinn.statistics.hibernate.service.DivisionTeamPlayerService;
import io.github.hylinn.statistics.hibernate.service.PlayerService;
import io.github.hylinn.statistics.hibernate.service.SkaterStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class SkaterStatisticsController {

    private DivisionTeamPlayerService divisionTeamPlayerService;
    private PlayerService playerService;

    @Autowired
    protected SkaterStatisticsController(DivisionTeamPlayerService divisionTeamPlayerService, PlayerService playerService) {
        this.divisionTeamPlayerService = divisionTeamPlayerService;
        this.playerService = playerService;
    }

    @RequestMapping("skater")
    public Collection<SkaterStatistics> skaterStatistics(@RequestParam(value="id", required = true) int id) {
        Collection<DivisionTeamPlayer> divisionTeamPlayers = divisionTeamPlayerService.findByPlayer(playerService.findById(id));

        Collection<SkaterStatistics> skaterStats = new ArrayList<>(divisionTeamPlayers.size());

        for (DivisionTeamPlayer divisionTeamPlayer : divisionTeamPlayers)
            skaterStats.add(divisionTeamPlayer.getSkaterStats());

        return skaterStats;
    }
}

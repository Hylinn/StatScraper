package io.github.hylinn.statistics.spring.controller;

import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeamPlayer;
import io.github.hylinn.statistics.hibernate.entity.Player;
import io.github.hylinn.statistics.hibernate.service.DivisionTeamPlayerService;
import io.github.hylinn.statistics.hibernate.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class DivisionTeamPlayerController {

    private DivisionTeamPlayerService divisionTeamPlayerService;
    private PlayerService playerService;

    @Autowired
    protected DivisionTeamPlayerController(DivisionTeamPlayerService divisionTeamPlayerService, PlayerService playerService) {
        this.divisionTeamPlayerService = divisionTeamPlayerService;
        this.playerService = playerService;
    }

    @RequestMapping("/player/teams")
    public Collection<DivisionTeam> playerTeams(@RequestParam(value="id", required = true) int id) {
        Player player = playerService.findById(id);
        Collection<DivisionTeamPlayer>divisionTeamPlayers = divisionTeamPlayerService.findByPlayer(player);

        Collection<DivisionTeam> divisionTeams = new ArrayList<>(divisionTeamPlayers.size());

        for (DivisionTeamPlayer divisionTeamPlayer : divisionTeamPlayers) {
            divisionTeams.add(divisionTeamPlayer.getDivisionTeam());
        }

        return divisionTeams;
    }
}

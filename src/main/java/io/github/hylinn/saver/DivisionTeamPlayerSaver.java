package io.github.hylinn.saver;

import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeamPlayer;
import io.github.hylinn.statistics.hibernate.entity.Player;
import io.github.hylinn.statistics.hibernate.service.Service;

import java.util.ArrayList;
import java.util.Collection;

public class DivisionTeamPlayerSaver extends AbstractEntitySaver<DivisionTeam, Player> {

    private final Service<DivisionTeamPlayer, Integer> service;
    private final Collection<Player> players = new ArrayList<>();

    public DivisionTeamPlayerSaver(Service<DivisionTeamPlayer, Integer> service) {
        this.service = service;
    }

    @Override
    protected Collection<Player> getCollection() {
        return players;
    }

    @Override
    protected void clearCollection() {
        players.clear();
    }

    @Override
    protected Object saveEntity(DivisionTeam divisionTeam, Player player) {
        DivisionTeamPlayer divisionTeamPlayer = new DivisionTeamPlayer(divisionTeam, player);
        return service.saveOrFind(divisionTeamPlayer);
    }
}

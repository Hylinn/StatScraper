package io.github.hylinn.saver;

import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import io.github.hylinn.statistics.hibernate.entity.Team;
import io.github.hylinn.statistics.hibernate.service.Service;

import java.util.ArrayList;
import java.util.Collection;

public class DivisionTeamSaver extends AbstractEntitySaver<LeagueSeasonDivision, Team> {

    private final Service<DivisionTeam, Integer> service;
    private final Collection<Team> teams = new ArrayList<>();

    public DivisionTeamSaver(Service<DivisionTeam, Integer> service) {
        this.service = service;
    }

    @Override
    protected Collection<Team> getCollection() {
        return teams;
    }

    @Override
    protected void clearCollection() {
        teams.clear();
    }

    @Override
    protected Object saveEntity(LeagueSeasonDivision leagueSeasonDivision, Team team) {
        DivisionTeam divisionTeam = new DivisionTeam(leagueSeasonDivision, team);
        return service.saveOrFind(divisionTeam);
    }
}

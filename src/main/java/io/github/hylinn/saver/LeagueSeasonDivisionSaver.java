package io.github.hylinn.saver;

import io.github.hylinn.statistics.hibernate.entity.Division;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import io.github.hylinn.statistics.hibernate.service.Service;

import java.util.ArrayList;
import java.util.Collection;

public class LeagueSeasonDivisionSaver extends AbstractEntitySaver<LeagueSeason, Division> {

    private final Service<LeagueSeasonDivision, Integer> service;
    private final Collection<Division> divisions = new ArrayList<>();

    public LeagueSeasonDivisionSaver(Service<LeagueSeasonDivision, Integer> service) {
        this.service = service;
    }

    @Override
    protected Collection<Division> getCollection() {
        return divisions;
    }

    @Override
    protected void clearCollection() {
        divisions.clear();
    }

    @Override
    protected Object saveEntity(LeagueSeason leagueSeason, Division division) {
        LeagueSeasonDivision leagueSeasonDivision = new LeagueSeasonDivision(leagueSeason, division);
        return service.saveOrFind(leagueSeasonDivision);
    }
}

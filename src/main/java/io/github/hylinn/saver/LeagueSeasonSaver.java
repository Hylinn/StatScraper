package io.github.hylinn.saver;

import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.entity.Season;
import io.github.hylinn.statistics.hibernate.service.Service;

import java.util.ArrayList;
import java.util.Collection;

public class LeagueSeasonSaver extends AbstractEntitySaver<League, Season> {

    private final Service<LeagueSeason, Integer> service;
    private final Collection<Season> seasons = new ArrayList<>();

    public LeagueSeasonSaver(Service<LeagueSeason, Integer> service) {
        this.service = service;
    }

    @Override
    protected Collection<Season> getCollection() {
        return seasons;
    }

    @Override
    protected void clearCollection() {
        seasons.clear();
    }

    @Override
    protected Object saveEntity(League league, Season season) {
        LeagueSeason leagueSeason = new LeagueSeason(league, season);
        return service.saveOrFind(leagueSeason);
    }
}

package io.github.hylinn.parser;

import io.github.hylinn.scraper.QueryParameters;
import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.service.Service;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Collection;

public class LeagueParser implements Parser<League> {

    private final Service<League, Integer> service;

    public LeagueParser(Service<League, Integer> service) {
        this.service = service;
    }

    public Collection<League> parse(Element element) {
        Element row = getLeagueRow(element);
        int leagueId  = getLeagueId(row);

        Collection<League> leagues = new ArrayList<>(1);
        leagues.add(createLeague(leagueId, row.text()));
        return leagues;
    }

    protected Element getLeagueRow(Element element) {
        return element.select("tr").first();
    }

    protected int getLeagueId(Element element) {
        String url = element.baseUri();
        QueryParameters params = new QueryParameters(url);

        return Integer.parseInt(params.get("league").get(0));
    }

    protected League createLeague(int id, String name) {
        League league = new League(id, name);
        league = service.saveOrFind(league);

        return league;
    }
}

package io.github.hylinn.parser;

import io.github.hylinn.scraper.QueryParameters;
import io.github.hylinn.statistics.hibernate.entity.Team;
import io.github.hylinn.statistics.hibernate.service.Service;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;

public class TeamParser implements Parser<Team> {

    private final Service<Team, Integer> service;

    public TeamParser(Service<Team, Integer> service) {
        this.service = service;
    }

    public Collection<Team> parse(Element element) {
        Elements rows = getRows(element);

        Collection<Team> teams = new ArrayList<>();
        for (Element row : rows) {
            if (isTeam(row)) {
                Element teamElement = getTeamElement(row);
                Team team = createTeam(teamElement);
                teams.add(team);
            }
        }

        return teams;
    }

    protected Elements getRows(Element element) {
        return element.select("tr");
    }

    protected boolean isTeam(Element element) {
        Elements team = element.select("td");
        if (team.size() > 0)
            return true;
        return false;
    }

    protected Element getTeamElement(Element row) {
        return row.select("td").first();
    }

    protected Team createTeam(Element element) {
        QueryParameters queryParameters = new QueryParameters(element.select("a").first().attr("href"));
        int id = Integer.parseInt(queryParameters.get("team").get(0));
        String name = element.text();

        Team team = new Team(id, name);
        team = service.saveOrFind(team);

        return team;
    }
}

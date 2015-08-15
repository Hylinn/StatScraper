package io.github.hylinn.parser;

import io.github.hylinn.scraper.QueryParameters;
import io.github.hylinn.statistics.hibernate.entity.Season;
import io.github.hylinn.statistics.hibernate.entity.TimeOfYear;
import io.github.hylinn.statistics.hibernate.service.Service;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;

public class SeasonParser implements Parser<Season> {

    private final Service<Season, Integer> service;
    private final Parser<TimeOfYear> parser;

    public SeasonParser(Service<Season, Integer> service, Parser<TimeOfYear> parser) {
        this.service = service;
        this.parser = parser;
    }

    public Collection<Season> parse(Element element) {
        Elements anchors = getAnchors(element);

        Collection<Season> seasons = new ArrayList<>(anchors.size());
        for (Element anchor : anchors) {
            TimeOfYear timeOfYear = parser.parse(anchor).iterator().next();
            seasons.add(createSeason(anchor, timeOfYear));
        }

        return seasons;
    }

    protected Elements getAnchors(Element element) {
        return element.select("a");
    }

    protected int getSeasonId(Element anchor) {
        QueryParameters params = new QueryParameters(anchor.attr("href"));
        int seasonId = Integer.parseInt(params.get("season").get(0));

        return seasonId;
    }

    protected int getYear(Element anchor) {
        String[] text = anchor.text().split(" ");

        int year = Integer.parseInt(text[3]);
        return year;
    }

    protected Season createSeason(Element anchor, TimeOfYear timeOfYear) {
        int id = getSeasonId(anchor);
        int year = getYear(anchor);
        Season season = new Season(id, year, timeOfYear);
        season = service.saveOrFind(season);

        return season;
    }
}

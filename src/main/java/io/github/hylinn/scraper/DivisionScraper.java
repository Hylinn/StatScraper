package io.github.hylinn.scraper;

import io.github.hylinn.parser.Parser;
import io.github.hylinn.saver.Saver;
import io.github.hylinn.scraper.URL.StatsURL;
import io.github.hylinn.statistics.hibernate.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collection;

public class DivisionScraper extends AbstractScraper<LeagueSeason, Division> {

    private final Saver<LeagueSeason, Division> saver;
    private final Parser<Division> parser;

    public DivisionScraper(Parser<Division> parser, Saver<LeagueSeason, Division> saver) {
        this.parser = parser;
        this.saver = saver;
    }

    @Override
    protected Element retrieveElement(LeagueSeason leagueSeason) {
        Document document = new Document("");
        try {
            document = Jsoup.connect(constructURL(leagueSeason)).get();
        }
        catch (IOException e) {

        }
        return document;
    }

    protected String constructURL(LeagueSeason leagueSeason) {
        int leagueId = leagueSeason.getLeague().getId();
        int seasonId = leagueSeason.getSeason().getId();
        return "" + new StatsURL(leagueId, seasonId);
    }

    @Override
    protected void save(LeagueSeason leagueSeason, Collection<Division> divisions) {
        saver.reset();
        for (Division division : divisions) {
            saver.add(division);
        }
        saver.save(leagueSeason);
    }

    @Override
    protected Parser<Division> getParser() {
        return parser;
    }
}

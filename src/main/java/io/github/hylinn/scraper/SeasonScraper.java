package io.github.hylinn.scraper;

import io.github.hylinn.parser.Parser;
import io.github.hylinn.saver.Saver;
import io.github.hylinn.scraper.URL.ArchiveURL;
import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.entity.Season;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collection;

public class SeasonScraper extends AbstractScraper<League, Season> {

    private final Parser<Season> parser;
    private final Saver<League, Season> saver;

    public SeasonScraper(Parser<Season> parser, Saver<League, Season> saver) {
        this.parser = parser;
        this.saver = saver;
    }

    @Override
    protected Element retrieveElement(League league) {
        Document document = new Document("");
        try {
            document = Jsoup.connect(constructURL(league.getId())).get();
        }
        catch (IOException e) {

        }
        return document;
    }

    @Override
    protected Parser<Season> getParser() {
        return parser;
    }

    @Override
    protected void save(League league, Collection<Season> seasons) {
        saver.reset();
        for (Season season : seasons) {
            saver.add(season);
        }
        saver.save(league);
    }

    protected String constructURL(int id) {
        return "" + new ArchiveURL(id);
    }
}

package io.github.hylinn.scraper;

import io.github.hylinn.parser.Parser;
import io.github.hylinn.scraper.URL.StatsURL;
import io.github.hylinn.statistics.hibernate.entity.League;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collection;

public class LeagueScraper extends AbstractScraper<Integer, League> {

    private final Parser<League> parser;

    public LeagueScraper(Parser<League> parser) {
        this.parser = parser;
    }

    @Override
    protected Element retrieveElement(Integer id) {
        Document document = new Document("");
        try {
            document = Jsoup.connect(constructURL(id)).get();
        }
        catch (IOException e) {

        }
        return document;
    }

    @Override
    protected Parser<League> getParser() {
        return parser;
    }

    @Override
    protected void save(Integer id, Collection<League> collection) {}

    protected String constructURL(int id) {
        return "" + new StatsURL(id);
    }
}

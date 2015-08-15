package io.github.hylinn.scraper;

import io.github.hylinn.parser.Parser;
import org.jsoup.nodes.Element;

import java.util.Collection;

public abstract class AbstractScraper<T, C> implements Scraper<T> {

    protected abstract Element retrieveElement(T t);
    protected abstract Parser<C> getParser();
    protected abstract void save(T t, Collection<C> collection);

    @Override
    public void scrape(Collection<T> collection) {
        for (T t : collection)
            scrape(t);
    }

    @Override
    public void scrape(T t) {
        Element element = retrieveElement(t);
        Collection collection = getParser().parse(element);
        save(t, collection);
    }
}

package io.github.hylinn.scraper;

import java.util.Collection;

public interface Scraper<T> {

    void scrape(Collection<T> collection);
    void scrape(T t);
}

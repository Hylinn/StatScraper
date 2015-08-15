package io.github.hylinn.parser;

import org.jsoup.nodes.Element;

import java.util.Collection;

public interface Parser<T> {

    Collection<T> parse(Element element);
}

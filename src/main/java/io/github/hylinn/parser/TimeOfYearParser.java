package io.github.hylinn.parser;

import io.github.hylinn.statistics.hibernate.entity.TimeOfYear;
import io.github.hylinn.statistics.hibernate.service.Service;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Collection;

public class TimeOfYearParser implements Parser<TimeOfYear> {

    private final Service<TimeOfYear, String> service;

    public TimeOfYearParser(Service<TimeOfYear, String> service) {
        this.service = service;
    }

    public Collection<TimeOfYear> parse(Element element) {
        String[] text = splitElementText(element.text());

        Collection<TimeOfYear> timeOfYears = new ArrayList<>(1);
        timeOfYears.add(createTimeOfYear(getName(text)));
        return timeOfYears;
    }

    protected String[] splitElementText(String text) {
        return text.split(" ");
    }

    protected TimeOfYear createTimeOfYear(String name) {
        TimeOfYear timeOfYear = new TimeOfYear(name);
        timeOfYear = service.saveOrFind(timeOfYear);

        return timeOfYear;
    }

    protected String getName(String[] text) {
        return text[2];
    }
}

package io.github.hylinn.parser;

import io.github.hylinn.statistics.hibernate.entity.Division;
import io.github.hylinn.statistics.hibernate.service.Service;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;

public class DivisionParser implements Parser<Division> {

    private final Service<Division, Integer> service;

    public DivisionParser(Service<Division, Integer> service) {
        this.service = service;
    }

    public Collection<Division> parse(Element element) {
        Elements rows = getRows(element);

        Collection<Division> divisions = new ArrayList<>();
        for (Element row : rows) {
            if (isDivision(row)) {
                Element divisionElement = getDivisionElement(row);
                Division division = createDivision(divisionElement);
                divisions.add(division);
            }
        }

        return divisions;
    }

    protected Elements getRows(Element element) {
        return element.select("tr");
    }

    protected boolean isDivision(Element element) {
        Elements division = element.select("a[name]");
        if (division.size() > 0)
            return true;
        return false;
    }

    protected Element getDivisionElement(Element row) {
        return row.select("a[name]").first();
    }

    protected Division createDivision(Element element) {
        int id = Integer.parseInt(element.attr("name"));
        String name = element.text();

        Division division = new Division(id, name);
        division = service.saveOrFind(division);

        return division;
    }
}

package io.github.hylinn.statistics.spring.controller;

import io.github.hylinn.parser.DivisionParser;
import io.github.hylinn.parser.Parser;
import io.github.hylinn.saver.LeagueSeasonDivisionSaver;
import io.github.hylinn.saver.Saver;
import io.github.hylinn.scraper.DivisionScraper;
import io.github.hylinn.scraper.Scraper;
import io.github.hylinn.statistics.hibernate.entity.Division;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeasonDivision;
import io.github.hylinn.statistics.hibernate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
public class DivisionScraperController extends AbstractScraperController {

    private final Scraper<LeagueSeason> scraper;
    private final Service<LeagueSeason, Integer> service;

    @Autowired
    public DivisionScraperController(Service<Division, Integer> divisionService, Service<LeagueSeason, Integer> leagueSeasonService, Service<LeagueSeasonDivision, Integer> leagueSeasonDivisionService) {
        Parser<Division> parser = createDivisionParser(divisionService);
        Saver<LeagueSeason, Division> saver = createLeagueSeasonDivisionSaver(leagueSeasonDivisionService);

        this.service = leagueSeasonService;
        this.scraper = new DivisionScraper(parser, saver);
    }

    @RequestMapping("/divisions")
    public ResponseEntity<Boolean> scrapeDivisions() {
        Collection<LeagueSeason> leagueSeasons = service.findAll();
        scraper.scrape(leagueSeasons);

        return success();
    }

    protected Parser<Division> createDivisionParser(Service<Division, Integer> service) {
        return new DivisionParser(service);
    }

    protected Saver<LeagueSeason, Division> createLeagueSeasonDivisionSaver(Service<LeagueSeasonDivision, Integer> service) {
        return new LeagueSeasonDivisionSaver(service);
    }
}

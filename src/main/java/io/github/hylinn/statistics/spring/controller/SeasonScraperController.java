package io.github.hylinn.statistics.spring.controller;

import io.github.hylinn.parser.Parser;
import io.github.hylinn.parser.SeasonParser;
import io.github.hylinn.parser.TimeOfYearParser;
import io.github.hylinn.saver.LeagueSeasonSaver;
import io.github.hylinn.saver.Saver;
import io.github.hylinn.scraper.Scraper;
import io.github.hylinn.scraper.SeasonScraper;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.service.Service;
import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.entity.Season;
import io.github.hylinn.statistics.hibernate.entity.TimeOfYear;
import io.github.hylinn.statistics.hibernate.service.LeagueSeasonService;
import io.github.hylinn.statistics.hibernate.service.LeagueService;
import io.github.hylinn.statistics.hibernate.service.SeasonService;
import io.github.hylinn.statistics.hibernate.service.TimeOfYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
public class SeasonScraperController extends AbstractScraperController {

    private final Service<League, Integer> service;
    private final Scraper<League> scraper;

    @Autowired
    public SeasonScraperController(Service<Season, Integer> seasonService, Service<LeagueSeason, Integer> leagueSeasonService, Service<TimeOfYear, String> timeOfYearService, Service<League, Integer> leagueService) {
        Parser<Season> parser = createSeasonParser(seasonService, createTimeOfYearParser(timeOfYearService));
        Saver<League, Season> saver = createLeagueSeasonSaver(leagueSeasonService);

        this.service = leagueService;
        this.scraper = createSeasonScraper(parser, saver);
    }

    @RequestMapping("/seasons")
    public ResponseEntity<Boolean> scrapeLeagues() {
        Collection<League> leagues = service.findAll();
        scraper.scrape(leagues);

        return success();
    }

    @RequestMapping("/season/{id}")
    public ResponseEntity<Boolean> scrapeLeague(@PathVariable int id) {
        League league = service.findById(id);
        scraper.scrape(league);

        return success();
    }

    protected Scraper<League> createSeasonScraper(Parser<Season> parser, Saver<League, Season> saver) {
        return new SeasonScraper(parser, saver);
    }

    protected Parser<Season> createSeasonParser(Service<Season, Integer> service, TimeOfYearParser parser) {
        return new SeasonParser(service, parser);
    }

    protected TimeOfYearParser createTimeOfYearParser(Service<TimeOfYear, String> service) {
        return new TimeOfYearParser(service);
    }

    protected Saver<League, Season> createLeagueSeasonSaver(Service<LeagueSeason, Integer> service) {
        return new LeagueSeasonSaver(service);
    }
}

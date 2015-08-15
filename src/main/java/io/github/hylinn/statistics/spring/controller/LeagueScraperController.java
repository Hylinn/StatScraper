package io.github.hylinn.statistics.spring.controller;

import io.github.hylinn.parser.LeagueParser;
import io.github.hylinn.parser.Parser;
import io.github.hylinn.scraper.LeagueScraper;
import io.github.hylinn.scraper.Scraper;
import io.github.hylinn.statistics.hibernate.service.Service;
import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@CrossOrigin
@RestController
public class LeagueScraperController extends AbstractScraperController {

    private static final Collection<Integer> LEAGUE_IDS = Arrays.asList(new Integer[]{1, 27});

    private final Scraper<Integer> scraper;

    @Autowired
    public LeagueScraperController(Service<League, Integer> leagueService) {
        Parser<League> parser = createLeagueParser(leagueService);
        this.scraper = createLeagueScraper(parser);
    }

    /*@RequestMapping("/")
    public ResponseEntity<Boolean> scrape() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        OldScraper scraper = new OldScraper(context);

        scraper.createLeagues();

        LeagueService leagueService = context.getBean(LeagueService.class);
        Collection<League> leagues = leagueService.findAll();

        for (League league : leagues) {
            scraper.createSeasons(league);
        }

        LeagueSeasonService leagueSeasonService = context.getBean(LeagueSeasonService.class);
        Collection<LeagueSeason> leagueSeasons = leagueSeasonService.findAll();

        for (LeagueSeason leagueSeason : leagueSeasons) {
            scraper.createLeagueSeasonDivisionTeams(leagueSeason);
        }

        DivisionTeamService divisionTeamService = context.getBean(DivisionTeamService.class);
        Collection<DivisionTeam> divisionTeams = divisionTeamService.findAll();

        for (DivisionTeam divisionTeam : divisionTeams) {
            scraper.createDivisionTeamPlayers(divisionTeam);
        }

        return success();
    }*/

    @RequestMapping("/leagues")
    public ResponseEntity<Boolean> scrapeLeagues() {
        scraper.scrape(LEAGUE_IDS);

        return success();
    }

    @RequestMapping("/league/{id}")
    public ResponseEntity<Boolean> scrapeLeague(@PathVariable int id) {
        scraper.scrape(id);

        return success();
    }

    protected Scraper<Integer> createLeagueScraper(Parser<League> parser) {
        return new LeagueScraper(parser);
    }

    protected Parser<League> createLeagueParser(Service<League, Integer> service) {
        return new LeagueParser(service);
    }
}

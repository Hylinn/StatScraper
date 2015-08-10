package io.github.hylinn.statistics.spring.controller;

import io.github.hylinn.scraper.Scraper;
import io.github.hylinn.statistics.hibernate.entity.DivisionTeam;
import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.entity.LeagueSeason;
import io.github.hylinn.statistics.hibernate.service.DivisionTeamService;
import io.github.hylinn.statistics.hibernate.service.LeagueSeasonService;
import io.github.hylinn.statistics.hibernate.service.LeagueService;
import io.github.hylinn.statistics.spring.configuration.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
public class ScraperController {

    @RequestMapping("/scrape")
    public ResponseEntity<Boolean> scrape() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Scraper scraper = new Scraper(context);

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

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}

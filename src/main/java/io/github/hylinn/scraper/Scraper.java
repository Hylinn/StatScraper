package io.github.hylinn.scraper;

import io.github.hylinn.statistics.hibernate.entity.*;
import io.github.hylinn.scraper.URL.ArchiveURL;
import io.github.hylinn.scraper.URL.StatsURL;
import io.github.hylinn.statistics.hibernate.service.*;
import io.github.hylinn.statistics.spring.configuration.ApplicationConfiguration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Collection;

public class Scraper {

    private static final int[] LEAGUE_IDS = {27};
    private ApplicationContext context;

    public Scraper(ApplicationContext context) {
        this.context = context;
    }

    public void createSeasons(League league) {
        TimeOfYearService timeOfYearService = context.getBean(TimeOfYearService.class);
        SeasonService seasonService = context.getBean(SeasonService.class);
        LeagueSeasonService leagueSeasonService = context.getBean(LeagueSeasonService.class);

        try {
            Document document = Jsoup.connect("" + new ArchiveURL(league.getId())).get();
            Elements anchors = document.select("a");

            for (Element element : anchors) {
                    QueryParameters params = new QueryParameters(element.attr("href"));
                    String[] text = element.text().split(" ");

                    int seasonId = Integer.parseInt(params.get("season").get(0));
                    int year = Integer.parseInt(text[3]);

                    TimeOfYear timeOfYear = timeOfYearService.findById(text[2]);
                    if (timeOfYear == null) {
                        timeOfYear = new TimeOfYear(text[2]);
                        timeOfYearService.save(timeOfYear);
                    }

                    Season season = seasonService.findById(seasonId);
                    if (season == null) {
                        season = new Season(seasonId, year, timeOfYear);
                        seasonService.save(season);
                    }

                    LeagueSeason leagueSeason = leagueSeasonService.findByUnique(league, season);
                    if (leagueSeason == null) {
                        leagueSeason = new LeagueSeason(league, season);
                        leagueSeasonService.save(leagueSeason);
                    }
            }
        }
        catch (IOException e) {

        }
    }

    public void createLeagues() {
        LeagueService leagueService = context.getBean(LeagueService.class);

        for (int id : LEAGUE_IDS) {
            try {
                Document document = Jsoup.connect("" + new StatsURL(id)).get();
                Elements rows = document.select("tr");

                if (leagueService.findById(id) == null) {
                    League league = new League(id, rows.first().text());
                    leagueService.save(league);
                }
            }
            catch (IOException e) {

            }
        }
    }

    public boolean isDivision(Element element) {
        Elements division = element.select("a[name]");
        if (division.size() > 0)
            return true;
        return false;
    }

    public boolean isTeam(Element element) {
        Elements team = element.select("td");
        if (team.size() > 0)
            return true;
        return false;
    }

    public boolean isTeamHeader(Element element) {
        Elements header = element.select("th");
        if (header.size() > 1 && isTeam(element.nextElementSibling()))
            return true;
        return false;

    }

    public void createLeagueSeasonDivisionTeams(LeagueSeason leagueSeason) {
        DivisionService divisionService = context.getBean(DivisionService.class);
        LeagueSeasonDivisionService leagueSeasonDivisionService = context.getBean(LeagueSeasonDivisionService.class);
        TeamService teamService = context.getBean(TeamService.class);
        TeamStatisticsService teamStatisticsService = context.getBean(TeamStatisticsService.class);
        DivisionTeamService divisionTeamService = context.getBean(DivisionTeamService.class);

        int leagueId = leagueSeason.getLeague().getId();
        int seasonId = leagueSeason.getSeason().getId();

        try {
            Document document = Jsoup.connect("" + new StatsURL(leagueId, seasonId)).get();
            Elements rows = document.select("tr");

            LeagueSeasonDivision leagueSeasonDivision = null;
            String[] headers = new String[0];
            for (int i = 1; i < rows.size(); i++) {
                if (isDivision(rows.get(i))) {
                    Element divisionElement = rows.get(i).select("a[name]").first();
                    int divisionId = Integer.parseInt(divisionElement.attr("name"));

                    Division division = divisionService.findById(divisionId);
                    if (division == null) {
                        division = new Division(divisionId, divisionElement.text());
                        divisionService.save(division);
                    }

                    leagueSeasonDivision = leagueSeasonDivisionService.findByUnique(leagueSeason, division);
                    if (leagueSeasonDivision == null) {
                        leagueSeasonDivision = new LeagueSeasonDivision(leagueSeason, division);
                        leagueSeasonDivisionService.save(leagueSeasonDivision);
                    }
                }
                else if (isTeam(rows.get(i))) {
                    Element teamElement = rows.get(i).select("td").first();
                    QueryParameters queryParameters = new QueryParameters(teamElement.select("a").first().attr("href"));
                    int teamId = Integer.parseInt(queryParameters.get("team").get(0));

                    Team team = teamService.findById(teamId);
                    if (team == null) {
                        team = new Team(teamId, teamElement.text());
                        teamService.save(team);
                    }

                    DivisionTeam divisionTeam = divisionTeamService.findByUnique(leagueSeasonDivision, team);
                    if (divisionTeam == null) {
                        divisionTeam = new DivisionTeam(leagueSeasonDivision, team);
                        divisionTeamService.save(divisionTeam);
                    }

                    TeamStatistics teamStats = teamStatisticsService.findById(divisionTeam.getId());
                    if (teamStats == null) {
                        teamStats = new TeamStatistics(divisionTeam);
                        Elements teamStatElements = teamElement.siblingElements();
                        for (int j = 1; j < headers.length; j++) {
                            if (headers[j].equals("w"))
                                teamStats.setWins(Integer.parseInt(teamStatElements.get(j - 1).text()));
                            else if (headers[j].equals("l"))
                                teamStats.setLosses(Integer.parseInt(teamStatElements.get(j - 1).text()));
                            else if (headers[j].equals("t"))
                                teamStats.setTies(Integer.parseInt(teamStatElements.get(j - 1).text()));
                            else if (headers[j].equals("otl"))
                                teamStats.setOvertimeLosses(Integer.parseInt(teamStatElements.get(j - 1).text()));
                            else if (headers[j].equals("gms"))
                                teamStats.setGameMisconducts(Integer.parseInt(teamStatElements.get(j - 1).text()));
                        }
                        teamStatisticsService.save(teamStats);
                    }
                }
                else if (isTeamHeader(rows.get(i))) {
                    Elements teamHeaderElements = rows.get(i).select("th");
                    headers = new String[teamHeaderElements.size()];

                    for (int j = 0; j < headers.length; j++) {
                        headers[j] = teamHeaderElements.get(j).text().toLowerCase();
                    }
                }
            }
        }
        catch (IOException e) {

        }
    }

    public void createDivisionTeamPlayers(DivisionTeam divisionTeam) {
        DivisionTeamPlayerService divisionTeamPlayerService = context.getBean(DivisionTeamPlayerService.class);
        PlayerService playerService = context.getBean(PlayerService.class);
        SkaterStatisticsService skaterStatisticsService = context.getBean(SkaterStatisticsService.class);
        GoalieStatisticsService goalieStatisticsService = context.getBean(GoalieStatisticsService.class);

        int leagueId = divisionTeam.getLeagueSeasonDivision().getLeagueSeason().getLeague().getId();
        int seasonId = divisionTeam.getLeagueSeasonDivision().getLeagueSeason().getSeason().getId();
        int teamId = divisionTeam.getTeam().getId();

        try {
            Document document = Jsoup.connect("" + new StatsURL(leagueId, seasonId, teamId)).get();
            Elements elements = document.select("tr th[colspan]");

            if (elements.size() > 1) {
                Elements skaterHeaderElements = elements.get(1).parent().nextElementSibling().children();
                Elements skaterStatElements = elements.get(1).parent().siblingElements();
                String[] skaterHeaders = new String[skaterHeaderElements.size()];

                for (int i = 0; i < skaterHeaders.length; i++) {
                    skaterHeaders[i] = skaterHeaderElements.get(i).text().toLowerCase();
                }

                for (int i = 1; i < skaterStatElements.size(); i++) {
                    SkaterStatistics skaterStats = null;
                    for (int j = 0; j < skaterHeaders.length; j++) {
                        String text = skaterStatElements.get(i).children().get(j).text();
                        if (skaterHeaders[j].equals("name")) {
                            Player player = playerService.findByUnique(text);
                            if (player == null) {
                                player = new Player(text, text);
                                playerService.save(player);
                            }

                            DivisionTeamPlayer divisionTeamPlayer = divisionTeamPlayerService.findByUnique(divisionTeam, player);
                            if (divisionTeamPlayer == null) {
                                divisionTeamPlayer = new DivisionTeamPlayer(divisionTeam, player);
                                divisionTeamPlayerService.save(divisionTeamPlayer);
                            }

                            skaterStats = skaterStatisticsService.findById(divisionTeamPlayer.getId());
                            if (skaterStats == null) {
                                skaterStats = new SkaterStatistics(divisionTeamPlayer);
                                skaterStatisticsService.save(skaterStats);
                            }
                        } else if (skaterHeaders[j].equals("#")) {
                            //TODO: Handle numbers
                        } else if (skaterHeaders[j].equals("gp"))
                            skaterStats.setGamesPlayed(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("goals"))
                            skaterStats.setGoals(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("ass."))
                            skaterStats.setAssists(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("ppg"))
                            skaterStats.setPowerplayGoals(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("ppa"))
                            skaterStats.setPowerplayAssists(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("shg"))
                            skaterStats.setShorthandedGoals(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("sha"))
                            skaterStats.setShorthandedAssists(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("gwg"))
                            skaterStats.setGameWinningGoals(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("gwa"))
                            skaterStats.setGameWinningAssists(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("psg"))
                            skaterStats.setPenaltyShotGoals(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("eng"))
                            skaterStats.setEmptyNetGoals(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("sog"))
                            skaterStats.setShotsOnGoal(Integer.parseInt(text));
                        else if (skaterHeaders[j].equals("pims"))
                            skaterStats.setPenaltyMinutes(Integer.parseInt(text));
                    }
                    skaterStatisticsService.update(skaterStats);
                }
            }

            if (elements.size() > 2) {
                Elements goalieHeaderElements = elements.get(2).parent().nextElementSibling().children();
                Elements goalieStatElements = elements.get(2).parent().siblingElements();
                String[] goalieHeaders = new String[goalieHeaderElements.size()];

                for (int i = 0; i < goalieHeaders.length; i++) {
                    goalieHeaders[i] = goalieHeaderElements.get(i).text().toLowerCase();
                }

                for (int i = 1; i < goalieStatElements.size(); i++) {
                    GoalieStatistics goalieStats = null;
                    for (int j = 0; j < goalieHeaders.length; j++) {
                        String text = goalieStatElements.get(i).children().get(j).text();
                        if (goalieHeaders[j].equals("name")) {
                            Player player = playerService.findByUnique(text);
                            if (player == null) {
                                player = new Player(text, text);
                                playerService.save(player);
                            }

                            DivisionTeamPlayer divisionTeamPlayer = divisionTeamPlayerService.findByUnique(divisionTeam, player);
                            if (divisionTeamPlayer == null) {
                                divisionTeamPlayer = new DivisionTeamPlayer(divisionTeam, player);
                                divisionTeamPlayerService.save(divisionTeamPlayer);
                            }

                            goalieStats = goalieStatisticsService.findById(divisionTeamPlayer.getId());
                            if (goalieStats == null) {
                                goalieStats = new GoalieStatistics(divisionTeamPlayer);
                                goalieStatisticsService.save(goalieStats);
                            }
                        } else if (goalieHeaders[j].equals("#")) {
                            //TODO: Handle numbers
                        } else if (goalieHeaders[j].equals("gp"))
                            goalieStats.setGamesPlayed(Integer.parseInt(text));
                        else if (goalieHeaders[j].equals("shots"))
                            goalieStats.setShots(Integer.parseInt(text));
                        else if (goalieHeaders[j].equals("ga"))
                            goalieStats.setGoalsAgainst(Integer.parseInt(text));
                        else if (goalieHeaders[j].equals("goals"))
                            goalieStats.setGoals(Integer.parseInt(text));
                        else if (goalieHeaders[j].equals("ass."))
                            goalieStats.setAssists(Integer.parseInt(text));
                        else if (goalieHeaders[j].equals("pims"))
                            goalieStats.setPenaltyMinutes(Integer.parseInt(text));
                    }
                    goalieStatisticsService.update(goalieStats);
                }
            }
        }
        catch (IOException e) {

        }
    }

    public static void main(String[] args) {
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
    }
}

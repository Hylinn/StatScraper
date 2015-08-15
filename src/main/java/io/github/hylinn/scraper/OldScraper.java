package io.github.hylinn.scraper;

import io.github.hylinn.scraper.URL.ArchiveURL;
import io.github.hylinn.statistics.hibernate.entity.*;
import io.github.hylinn.statistics.hibernate.service.LeagueSeasonService;
import io.github.hylinn.statistics.hibernate.service.SeasonService;
import io.github.hylinn.statistics.hibernate.service.TimeOfYearService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class OldScraper {


    private ApplicationContext context;

    public OldScraper(ApplicationContext context) {
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

                    System.out.println("Creating TimeOfYear " + text[2] + "...");
                    TimeOfYear timeOfYear = timeOfYearService.findById(text[2]);
                    if (timeOfYear == null) {
                        timeOfYear = new TimeOfYear(text[2]);
                        timeOfYearService.save(timeOfYear);
                    }

                    System.out.println("Creating Season " + seasonId + "...");
                    Season season = seasonService.findById(seasonId);
                    if (season == null) {
                        season = new Season(seasonId, year, timeOfYear);
                        seasonService.save(season);
                    }

                    System.out.println("Creating LeagueSeason " + league + ", " + season + "...");
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

    /*public void createLeagues(Parser<League> parser) {
        LeagueService leagueService = context.getBean(LeagueService.class);
        Collection<League> leagues = parser.parse();

        for (League league : leagues) {
            if (leagueService.findById(league.getId()) == null)
                leagueService.save(league);
        }
    }*/

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
        /*DivisionService divisionService = context.getBean(DivisionService.class);
        LeagueSeasonTeamService leagueSeasonTeamService = context.getBean(LeagueSeasonTeamService.class);
        TeamService teamService = context.getBean(TeamService.class);
        TeamStatisticsService teamStatisticsService = context.getBean(TeamStatisticsService.class);
        DivisionTeamService divisionTeamService = context.getBean(DivisionTeamService.class);

        int leagueId = leagueSeason.getLeague().getId();
        int seasonId = leagueSeason.getSeason().getId();

        try {
            Document document = Jsoup.connect("" + new StatsURL(leagueId, seasonId)).get();
            Elements rows = document.select("tr");

            LeagueSeasonDivision leagueSeasonTeam = null;
            String[] headers = new String[0];
            for (int i = 1; i < rows.size(); i++) {
                if (isDivision(rows.get(i))) {
                    Element divisionElement = rows.get(i).select("a[name]").first();
                    int divisionId = Integer.parseInt(divisionElement.attr("name"));

                    System.out.println("Creating Division " + divisionId + "...");
                    Division division = divisionService.findById(divisionId);
                    if (division == null) {
                        division = new Division(divisionId, divisionElement.text());
                        divisionService.save(division);
                    }

                    System.out.println("Creating LeagueSeasonDivision " + leagueSeason + ", " + division + "...");
                    leagueSeasonTeam = leagueSeasonTeamService.findByUnique(leagueSeason, division);
                    if (leagueSeasonTeam == null) {
                        leagueSeasonTeam = new LeagueSeasonDivision(leagueSeason, division);
                        leagueSeasonTeamService.save(leagueSeasonTeam);
                    }
                }
                else if (isTeam(rows.get(i))) {
                    Element teamElement = rows.get(i).select("td").first();
                    QueryParameters queryParameters = new QueryParameters(teamElement.select("a").first().attr("href"));
                    int teamId = Integer.parseInt(queryParameters.get("team").get(0));

                    System.out.println("Creating Team " + teamId + "...");
                    Team team = teamService.findById(teamId);
                    if (team == null) {
                        team = new Team(teamId, teamElement.text());
                        teamService.save(team);
                    }

                    System.out.println("Creating DivisionTeam " + leagueSeasonTeam + ", " + team + "...");
                    DivisionTeam divisionTeam = divisionTeamService.findByUnique(leagueSeasonTeam, team);
                    if (divisionTeam == null) {
                        divisionTeam = new DivisionTeam(leagueSeasonTeam, team);
                        divisionTeamService.save(divisionTeam);
                    }

                    System.out.println("Creating TeamStatistics " + divisionTeam.getId() + "...");
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

        }*/
    }

    public void createDivisionTeamPlayers(DivisionTeam divisionTeam) {
        /*TeamPlayerService teamPlayerService = context.getBean(TeamPlayerService.class);
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

                            System.out.println("Creating DivisionTeamPlayer " + divisionTeam + ", " + player + "...");
                            DivisionTeamPlayer teamPlayer = teamPlayerService.findByUnique(divisionTeam, player);
                            if (teamPlayer == null) {
                                teamPlayer = new DivisionTeamPlayer(divisionTeam, player);
                                teamPlayerService.save(teamPlayer);
                            }

                            System.out.println("Creating SkaterStatistics " + teamPlayer.getId() + "...");
                            skaterStats = skaterStatisticsService.findById(teamPlayer.getId());
                            if (skaterStats == null) {
                                skaterStats = new SkaterStatistics(teamPlayer);
                                skaterStatisticsService.save(skaterStats);
                            }
                        } else if (skaterHeaders[j].equals("#") && canParseInteger(text)) {
                            skaterStats.setNumber(Integer.parseInt(text));
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

                            System.out.println("Creating DivisionTeamPlayer " + divisionTeam + ", " + player + "...");
                            DivisionTeamPlayer teamPlayer = teamPlayerService.findByUnique(divisionTeam, player);
                            if (teamPlayer == null) {
                                teamPlayer = new DivisionTeamPlayer(divisionTeam, player);
                                teamPlayerService.save(teamPlayer);
                            }

                            System.out.println("Creating GoalieStatistics " + teamPlayer.getId() + "...");
                            goalieStats = goalieStatisticsService.findById(teamPlayer.getId());
                            if (goalieStats == null) {
                                goalieStats = new GoalieStatistics(teamPlayer);
                                goalieStatisticsService.save(goalieStats);
                            }
                        } else if (goalieHeaders[j].equals("#") && canParseInteger(text)) {
                            goalieStats.setNumber(Integer.parseInt(text));
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

        }*/
    }

    private boolean canParseInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}

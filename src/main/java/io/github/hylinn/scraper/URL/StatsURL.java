package io.github.hylinn.scraper.URL;

public class StatsURL {
    private final String url;

    private static final String DISPLAY_URL = "http://stats.siahl.org/display-stats.php?";
    private static final String SCHEDULE_URL = "http://stats.siahl.org/display-schedule.php?";

    public StatsURL(int league, int season, int team) { url = SCHEDULE_URL + "league=" + league + "&season=" + season + "&team=" + team; }

    public StatsURL(int league, int season) {
        url = DISPLAY_URL + "league=" + league + "&season=" + season;
    }

    public StatsURL(int league) {
        url = DISPLAY_URL + "league=" + league;
    }

    @Override
    public String toString() {
        return url;
    }
}

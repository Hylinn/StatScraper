package io.github.hylinn.scraper.URL;

public class StatsURL {

    private final int league;
    private final int season;

    private static final String DISPLAY_URL = "http://stats.siahl.org/display-stats.php?league=%1$d&season=%2$d";

    public StatsURL(int league, int season) {
        this.league = league;
        this.season = season;
    }

    @Override
    public String toString() {
        return String.format(DISPLAY_URL, league, season);
    }
}

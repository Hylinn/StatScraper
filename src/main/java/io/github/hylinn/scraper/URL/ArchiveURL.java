package io.github.hylinn.scraper.URL;

public class ArchiveURL {

    private final int league;

    private static final String ARCHIVE_URL = "http://stats.siahl.org/display-archives.php?league=%1$d";

    public ArchiveURL(int league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return String.format(ARCHIVE_URL, league);
    }
}

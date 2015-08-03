package io.github.hylinn.scraper;

import io.github.hylinn.statistics.hibernate.entity.League;
import io.github.hylinn.statistics.hibernate.entity.TimeOfYear;
import io.github.hylinn.statistics.hibernate.entity.Season;
import io.github.hylinn.scraper.URL.ArchiveURL;
import io.github.hylinn.scraper.URL.StatsURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper {

    private static final int[] LEAGUE_IDS = {27};

    public void createSeasons(League league) {
        try {
            Document document = Jsoup.connect("" + new ArchiveURL(league.getId())).get();
            Elements anchors = document.select("a");

            for (Element element : anchors) {
                QueryParameters params = new QueryParameters(element.attr("href"));
                String[] text = element.text().split(" ");

                int seasonId = Integer.parseInt(params.get("season").get(0));
                int year = Integer.parseInt(text[3]);

                TimeOfYear playSeason = new TimeOfYear(text[2]);
                Season season = new Season(seasonId, year, playSeason);
                //league.getSeasons().add(season);
            }
        }
        catch (IOException e) {

        }
    }

    public void createLeagues() {
        for (int id : LEAGUE_IDS) {
            try {
                Document document = Jsoup.connect("" + new StatsURL(id)).get();
                Element name = document.select("tr").first();

                League league = new League(id, name.text());
            }
            catch (IOException e) {

            }
        }
    }

    public void createDivisions() {

    }

    public static void main(String[] args) {
        Scraper scraper = new Scraper();

        scraper.createLeagues();

        League league = new League(27, "SIAHL@OK");
        scraper.createSeasons(league);
    }
}

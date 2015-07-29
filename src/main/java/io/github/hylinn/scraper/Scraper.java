package io.github.hylinn.scraper;

import io.github.hylinn.scraper.URL.ArchiveURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scraper {

    public void createSeasonLeagues(int league) {
        try {
            Document document = Jsoup.connect("" + new ArchiveURL(league)).get();
            Elements anchors = document.select("a");

            for (Element element : anchors) {
                Map<String, List<String>> parameters = getQueryParameters(element.attr("href"));
                //System.out.println(parameters.get("season").get(0));
            }
        }
        catch (IOException e) {

        }
    }

    private static Map<String, List<String>> getQueryParameters(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }

            return params;
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }
    }

    public static void main(String[] args) {
        Scraper scraper = new Scraper();

        scraper.createSeasonLeagues(27);

    }
}

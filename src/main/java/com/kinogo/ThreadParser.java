package com.kinogo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadParser implements Callable<List<Film>> {
    private String webSiteURL;
    private int beginPage;
    private int endPage;

    public ThreadParser(String webSiteURL, int beginPage, int endPage) {
        this.webSiteURL = webSiteURL;
        this.beginPage = beginPage;
        this.endPage = endPage;
    }

    @Override
    public List<Film> call() throws Exception {
        List<Film> films = new ArrayList<>();
        for (int i = beginPage; i <= endPage; i++) {
            String newURL = webSiteURL.concat(String.valueOf(i));
            Document doc = Jsoup.connect(newURL)
                    .userAgent("Safari")
                    .get();
            films.addAll(ParseKinogo.parseFilms(doc));
        }
        return films;
    }
}

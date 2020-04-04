package com.kinogo;

import com.service.SecurityServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ParseKinogo {
    private static final Logger logger = LogManager.getLogger(SecurityServiceImpl.class);

    public static List<Film> parseFilms(Document document) {
        List<Film> films = new ArrayList<>();
        Elements content = getFilmContent(document);
        for (Element element : content) {
            films.add(getFilm(element));
            logger.info("Film was parsed. Film details = " + getFilm(element));
        }
        return films;
    }

    private static Elements getFilmContent(Document document) {
        return document.getElementsByClass("shortimg");
    }

    private static Film getFilm(Element type) {
        Film film = new Film();
        film.setName(getFilmName(type));
        film.setYear(getYear(type));
        film.setCountry(getCountry(type));
        film.setType(getGenre(type));
        film.setQuality(getQuality(type));
        film.setTranslation(getTranslation(type));
        film.setContinuance(getContinuance(type));
        film.setDate(getDate(type));
        film.setLink(getLink(type));
        film.setDescription(getDescription(type));
        return film;
    }

    private static String getFilmName(Element filmName) {
        String stringRegexNumber = "\\([^()]*\\)";
        Elements ariaLabel = filmName.getElementsByAttribute("aria-label");
        String filmTitle = ariaLabel.attr("aria-label");
        if (filmTitle != null && !filmTitle.isEmpty()) {
            String[] strings = filmTitle.split(" ");
            for (String splittedString : strings) {
                if (splittedString.matches(stringRegexNumber)) {
                    int titleNumberLength = splittedString.length();
                    if (titleNumberLength >= 4 & titleNumberLength <= 6) {
                        filmTitle = filmTitle.replace(splittedString, "");
                    }
                }
            }
            return filmTitle.trim();
        } else {
            return "No information";
        }
    }

    private static String getYear(Element type) {
        Elements year = type.select("b:contains(Год выпуска:)");
        String yearText = year.next().text();
        if (yearText != null && !yearText.isEmpty()) {
            return yearText;
        } else {
            return "No information";
        }
    }

    private static List<String> getCountry(Element type) {
        ListTypes<Node> country = new ListTypes<>("b:contains(Страна:)", "<br>", Node.class);
        List<String> types = country.getTypes(type);
        return types;
    }

    private static List<String> getGenre(Element type) {
        ListTypes<Element> genre = new ListTypes<>("b:contains(Жанр:)", "Качество:", Element.class);
        List<String> types = genre.getTypes(type);
        return types;
    }

    private static String getValue(Element type, String pattern) {
        Element element = type.select(pattern).first();
        if (element != null && !element.toString().isEmpty()) {
            Node nodeNextElement = element.nextSibling();
            return nodeNextElement.toString().trim();
        } else {
            return "No information";
        }
    }

    private static String getQuality(Element type) {
        return getValue(type, "b:contains(Качество:)");
    }

    private static String getTranslation(Element type) {
        return getValue(type, "b:contains(Перевод:)");
    }

    private static String getContinuance(Element type) {
        return getValue(type, "b:contains(Продолжительность:)");
    }

    private static String getDate(Element type) {
        return getValue(type, "b:contains(Премьера:)");
    }

    private static String getDescription(Element type) {
        Element descriptions = type.select(".shortimg").first();
        String replacedDescr = descriptions.text().replace("Лицензия", "").trim();
        if (descriptions != null && !descriptions.text().isEmpty()) {
            return replacedDescr;
        } else {
            return "No information";
        }
    }

    private static String getLink(Element type) {
        Elements ariaLabel = type.getElementsByAttribute("aria-label");
        String link = ariaLabel.attr("href");
        if (link != null && !link.isEmpty()) {
            return link;
        } else {
            return "No information";
        }
    }
}



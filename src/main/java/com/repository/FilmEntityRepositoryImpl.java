package com.repository;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.kinogo.Film;
import com.kinogo.MainKinogoParse;
import com.model.DescriptionWork;
import com.model.PaginationResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@Repository
@Transactional
public class FilmEntityRepositoryImpl implements FilmEntityRepository {
    private static final Logger logger = LogManager.getLogger(FilmEntityRepositoryImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    MainKinogoParse mainKinogoParse;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Film> parse(int num) throws Exception {
        List<Film> parse = mainKinogoParse.parse(num);
        putFilmnameDescrToFile(parse);
        return parse;
    }

    @Override
    public FilmEntity getFilmByFilmName(String name) {
        List<FilmEntity> filmEntities = new ArrayList<FilmEntity>();

        filmEntities = getSession().createQuery("from FilmEntity where name=?1").setParameter(1, name)
                .list();

        if (filmEntities.size() > 0) {
            return filmEntities.get(0);
        } else {
            name = name + "?";
            filmEntities = getSession().createQuery("from FilmEntity where name=?1").setParameter(1, name)
                    .list();
            if (filmEntities.size() > 0) {
                return filmEntities.get(0);
            } else {
                return null;
            }
        }
    }

    @Override
    public FilmEntity saveFilms(FilmEntity film, String language) throws Exception {
        if (getFilmByFilmName(film.getName()) == null) {
            double min = 0.0;
            double max = 2.10;
            double diff = max - min;
            DecimalFormat formatter = new DecimalFormat("#0.00");
            double randomValue = min + Math.random() * diff;
            double tempRes = Math.floor(randomValue * 10);
            double price = tempRes / 10;
            String priceFormatted = formatter.format(price);
            if (priceFormatted.equals("0,00")) {
                film.setPrice("free");
            } else {
                film.setPrice(priceFormatted);
            }
            getSession().persist(film);
            logger.info("Film was saved. Film details = " + film);
            return null;
        }
        return null;
    }


    @Override
    public PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage) {
        String sql = "from FilmEntity";

        Query query = getSession().createQuery(sql);


        logger.info("GetAllPagination Films method was successfully done");
        return new PaginationResult<FilmEntity>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public FilmEntity getFilmById(long id) {
        FilmEntity film = (FilmEntity) getSession().get(FilmEntity.class, id);
        return film;
    }

    @Override
    public String getFilmDescription(FilmEntity filmEntity) throws IOException {
        String description = null;
        DescriptionWork descriptionWork = new DescriptionWork();
        Map<String, String> map = descriptionWork.readFromFile();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String filmName = filmEntity.getName();
            int colonIndex = filmName.indexOf(":");
            if (colonIndex != -1) {
                String filmNameNoColon = filmName.substring(0, colonIndex).concat(filmName.substring(colonIndex + 1, filmName.length()));
                if (entry.getKey().equals(filmNameNoColon)) {
                    description = entry.getValue();
                    break;
                }
            } else {
                if (entry.getKey().equals(filmName)) {
                    description = entry.getValue();
                    break;
                } else {
                    description = "Sorry, no information";
                }
            }
        }
        return description;
    }

    public void putFilmnameDescrToFile(List<Film> films) {
        Map<String, String> map = new HashMap<>();
        for (Film film : films) {
            map.put(film.getName(), film.getDescription());
        }
        DescriptionWork dw = new DescriptionWork(map);
        dw.writeToFile(map);
    }

    @Override
    public void delete(long id) {
        FilmEntity filmById = getFilmById(id);
        Set<OrderEntity> orders = filmById.getOrders();
        for (OrderEntity order : orders) {
            order.removeFilm(filmById);
        }
        getSession().delete(filmById);

    }
}

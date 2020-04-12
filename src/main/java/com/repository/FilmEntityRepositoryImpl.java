package com.repository;

import com.entity.FilmEntity;
import com.entity.UserEntity;
import com.kinogo.Film;
import com.kinogo.MainKinogoParse;
import com.model.PaginationResult;
import com.service.SecurityServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

@Repository
@Transactional
public class FilmEntityRepositoryImpl implements FilmEntityRepository {
    private static final Logger logger = LogManager.getLogger(SecurityServiceImpl.class);
    private static List<Film> filmList = new ArrayList<>();

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
        for (Film film : parse) {
            filmList.add(film);
        }
        removeDublicates(filmList);
        return parse;
    }

    @Override
    public FilmEntity getFilmByFilmname(String name) {
        List<FilmEntity> filmEntities = new ArrayList<FilmEntity>();

        filmEntities = getSession().createQuery("from FilmEntity where name=?1").setParameter(1, name)
                .list();

        if (filmEntities.size() > 0) {
            return filmEntities.get(0);
        } else {
            return null;
        }
    }

    @Override
    public FilmEntity saveFilms(FilmEntity film, String language) throws Exception {
        if (getFilmByFilmname(film.getName()) == null) {
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
    public String getFilmDescription(FilmEntity filmEntity) {
        String description = null;
        String nameEntity = filmEntity.getName();
        for (Film filmFromList : filmList) {
            if (filmFromList.getName().equals(nameEntity)) {
                description = filmFromList.getDescription();
                if (description == null || description.isEmpty()) {
                    description = "Sorry, no information";
                }
            }
        }
        return description;
    }

    @Override
    public void delete(long id) {
        FilmEntity filmById = getFilmById(id);
        getSession().delete(filmById);
    }

    private List<Film> removeDublicates(List<Film> filmList) {
        Set<Film> set = new LinkedHashSet<>();
        set.addAll(filmList);
        filmList.clear();
        filmList.addAll(set);

        return filmList;
    }
}

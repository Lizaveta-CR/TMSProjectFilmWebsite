package com.repository;

import com.entity.FilmEntity;
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
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

@Repository
@Transactional
public class FilmEntityRepositoryImpl implements FilmEntityRepository {
    private static final Logger logger = LogManager.getLogger(SecurityServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    MainKinogoParse mainKinogoParse;

//    @Autowired
//    LocaleChangeInterceptor localeInterceptor;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Film> parse(int num) throws Exception {
        return mainKinogoParse.parse(num);
    }

    @Override
    public FilmEntity saveFilms(FilmEntity film, String language) throws Exception {
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


    @Override
    public PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage) {
        String sql = "from FilmEntity";

        Query query = getSession().createQuery(sql);


        logger.info("GetAllPagination Films method was successfully done");
        return new PaginationResult<FilmEntity>(query, page, maxResult, maxNavigationPage);
    }

//    @Override
//    public List<FilmEntity> getAll() {
//        Query fromOrderEntity = getSession().createQuery("from FilmEntity");
//        List resultList = fromOrderEntity.getResultList();
//        logger.info("GetAllList Films method was successfully done");
//        return resultList;
//    }

    @Override
    public FilmEntity getFilmById(long id) {
        FilmEntity film = (FilmEntity) getSession().get(FilmEntity.class, id);
        return film;
    }

//    @Override
////    @Transactional
//    public void update(FilmEntity film) {
//        FilmEntity filmById = getFilmById(film.getFilm_id());
//        getSession().saveOrUpdate(filmById);
//    }

    @Override
    public void delete(long id) {
        FilmEntity filmById = getFilmById(id);
        getSession().delete(filmById);
    }

}

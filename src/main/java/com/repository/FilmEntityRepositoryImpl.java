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

import java.util.List;

@Repository
@Transactional
public class FilmEntityRepositoryImpl implements FilmEntityRepository {
    private static final Logger logger = LogManager.getLogger(SecurityServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    MainKinogoParse mainKinogoParse;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Film> parse(int num) throws Exception {
        return mainKinogoParse.parse(num);
    }

    @Override
    public FilmEntity saveFilms(FilmEntity film) throws Exception {
        getSession().persist(film);
        logger.info("Film was saved. Film details = " + film);
        return null;
    }

    @Override
    public List<FilmEntity> getAll() {
        Query fromOrderEntity = getSession().createQuery("from FilmEntity");
        List resultList = fromOrderEntity.getResultList();
        logger.info("GetAll Films method was successfully done");
        return resultList;
    }

    @Override
    public PaginationResult<FilmEntity> queryFilms(int page, int maxResult, int maxNavigationPage) {
        return queryFilms(page, maxResult, maxNavigationPage, null);
    }

    @Override
    public PaginationResult<FilmEntity> queryFilms(int page, int maxResult, int maxNavigationPage, String likeName) {
        String sql = "from FilmEntity";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        Query query = getSession().createQuery(sql);//nullPointerException!

        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<FilmEntity>(query, page, maxResult, maxNavigationPage);
    }

}

package com.repository;


import com.entity.FilmEntity;
import com.kinogo.Film;
import com.model.PaginationResult;

import java.util.List;
import java.util.Locale;

public interface FilmEntityRepository {
    List<Film> parse(int num) throws Exception;

    FilmEntity getFilmByFilmname(String name);

    FilmEntity saveFilms(FilmEntity films, String language) throws Exception;

    PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage);

//    List<FilmEntity> getAll();

    FilmEntity getFilmById(long id);

//    void update(FilmEntity film);

    String getFilmDescription(FilmEntity filmEntity);


    void delete(long id);

}

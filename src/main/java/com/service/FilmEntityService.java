package com.service;

import com.entity.FilmEntity;
import com.kinogo.Film;
import com.model.PaginationResult;

import java.util.List;
import java.util.Locale;

public interface FilmEntityService {
    List<Film> parse(int num) throws Exception;

    FilmEntity getFilmByFilmName(String name);

    void saveFilms(List<Film> films, String language) throws Exception;

    PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage);

//    List<FilmEntity> getAll();

    FilmEntity getFilmById(long id);

//    void update(FilmEntity film);

    String getFilmDescription(FilmEntity filmEntity);

    void delete(long id);
}

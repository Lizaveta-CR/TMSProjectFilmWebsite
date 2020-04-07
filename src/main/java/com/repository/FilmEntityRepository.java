package com.repository;


import com.entity.FilmEntity;
import com.kinogo.Film;
import com.model.PaginationResult;

import java.util.List;

public interface FilmEntityRepository {
    List<Film> parse(int num) throws Exception;

    FilmEntity saveFilms(FilmEntity films) throws Exception;

    List<FilmEntity> getAll();

    PaginationResult<FilmEntity> queryFilms(int page, int maxResult, int maxNavigationPage);

    PaginationResult<FilmEntity> queryFilms(int page, int maxResult, int maxNavigationPage, String likeName);
}

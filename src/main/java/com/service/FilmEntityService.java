package com.service;

import com.entity.FilmEntity;
import com.kinogo.Film;
import com.model.PaginationResult;

import java.util.List;

public interface FilmEntityService {
    List<Film> parse(int num) throws Exception;

    void saveFilms(List<Film> films) throws Exception;

    List<FilmEntity> getAll();

    PaginationResult<FilmEntity> queryFilms(int page, int maxResult, int maxNavigationPage);

    PaginationResult<FilmEntity> queryFilms(int page, int maxResult, int maxNavigationPage, String likeName);
}

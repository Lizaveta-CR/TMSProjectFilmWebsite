package com.service;

import com.entity.FilmEntity;
import com.kinogo.Film;
import com.model.PaginationResult;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public interface FilmEntityService {
    List<Film> parse(int num) throws Exception;

    FilmEntity getFilmByFilmName(String name);

    void saveFilms(List<Film> films, String language) throws Exception;

    void delete(long id);

    PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage);

    FilmEntity getFilmById(long id);

    String getFilmDescription(FilmEntity filmEntity) throws IOException;


}

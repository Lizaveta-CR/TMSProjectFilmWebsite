package com.repository;


import com.entity.FilmEntity;
import com.kinogo.Film;
import com.model.PaginationResult;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public interface FilmEntityRepository {
    List<Film> parse(int num) throws Exception;

    FilmEntity getFilmByFilmName(String name);

    FilmEntity saveFilms(FilmEntity films, String language) throws Exception;

    PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage);

    FilmEntity getFilmById(long id);

    String getFilmDescription(FilmEntity filmEntity) throws IOException;

    void delete(long id);
}

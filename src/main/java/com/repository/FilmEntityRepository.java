package com.repository;


import com.entity.FilmEntity;
import com.FilmStorageKinogo.Film;
import com.model.PaginationResult;

import java.io.IOException;
import java.util.List;

public interface FilmEntityRepository {
    List<Film> parse(int num) throws Exception;

    FilmEntity getFilmByFilmName(String name);

    FilmEntity saveFilms(FilmEntity films) throws Exception;

    PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage);

    FilmEntity getFilmById(long id);

    String getFilmDescription(FilmEntity filmEntity) throws IOException;

    void delete(long id);

    void save(FilmEntity filmEntity);
}

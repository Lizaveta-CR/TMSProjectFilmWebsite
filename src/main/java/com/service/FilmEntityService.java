package com.service;

import com.entity.FilmEntity;
import com.FilmStorageKinogo.Film;
import com.model.PaginationResult;

import java.io.IOException;
import java.util.List;

public interface FilmEntityService {
    List<Film> parse(int num) throws Exception;

    FilmEntity getFilmByFilmName(String name);

    void saveFilms(List<Film> films, String language) throws Exception;

    void save(FilmEntity filmEntity);

    void delete(long id);

    PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage);

    FilmEntity getFilmById(long id);

    String getFilmDescription(FilmEntity filmEntity) throws IOException;


}

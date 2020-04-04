package com.repository;


import com.entity.FilmEntity;
import com.kinogo.Film;

import java.util.List;

public interface FilmEntityRepository {
    List<Film> parse(int num) throws Exception;

    FilmEntity saveFilms(FilmEntity films) throws Exception;
}

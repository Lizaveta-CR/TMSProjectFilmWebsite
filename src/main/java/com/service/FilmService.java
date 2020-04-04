package com.service;

import com.kinogo.Film;

import java.util.List;

public interface FilmService {
    List<Film> parse(int num) throws Exception;

    void saveFilms(List<Film> films) throws Exception;
}

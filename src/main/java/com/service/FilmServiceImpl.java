package com.service;

import com.mapper.FilmMapper;
import com.repository.FilmEntityRepository;
import com.kinogo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmMapper mapper;
    @Autowired
    FilmEntityRepository filmEntityRepository;

    @Override
    public List<Film> parse(int num) throws Exception {
        return filmEntityRepository.parse(num);
    }

    @Override
    public void saveFilms(List<Film> films) throws Exception {
        for (Film film : films) {
            mapper.toDto(filmEntityRepository.saveFilms(mapper.toEntity(film)));
        }
    }
}

package com.service;

import com.entity.FilmEntity;
import com.mapper.FilmMapper;
import com.model.PaginationResult;
import com.repository.FilmEntityRepository;
import com.kinogo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmEntityServiceImpl implements FilmEntityService {
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

    @Override
    public List<FilmEntity> getAll() {
        return filmEntityRepository.getAll();
    }

    @Override
    public PaginationResult<FilmEntity> queryFilms(int page, int maxResult, int maxNavigationPage) {
        return filmEntityRepository.queryFilms(page, maxResult, maxNavigationPage);
    }

    @Override
    public PaginationResult<FilmEntity> queryFilms(int page, int maxResult, int maxNavigationPage, String likeName) {
        return filmEntityRepository.queryFilms(page, maxResult, maxNavigationPage, likeName);
    }
}

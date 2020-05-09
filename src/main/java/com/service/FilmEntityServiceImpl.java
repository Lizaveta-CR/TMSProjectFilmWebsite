package com.service;

import com.entity.FilmEntity;
import com.mapper.FilmMapper;
import com.model.PaginationResult;
import com.repository.FilmEntityRepository;
import com.FilmStorageKinogo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public FilmEntity getFilmByFilmName(String name) {
        return filmEntityRepository.getFilmByFilmName(name);
    }


    @Override
    public void saveFilms(List<Film> films) throws Exception {
        for (Film film : films) {
            mapper.toDto(filmEntityRepository.saveFilms(mapper.toEntity(film)));
        }
    }

    @Override
    public void save(FilmEntity filmEntity) {
        filmEntityRepository.save(filmEntity);
    }

    @Override
    public PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage) {
        return filmEntityRepository.getAll(page, maxResult, maxNavigationPage);
    }

    @Override
    public FilmEntity getFilmById(long id) {
        return filmEntityRepository.getFilmById(id);
    }

    @Override
    public String getFilmDescription(FilmEntity filmEntity) throws IOException {
        return filmEntityRepository.getFilmDescription(filmEntity);
    }

    @Override
    public void delete(long id) {
        filmEntityRepository.delete(id);
    }
}

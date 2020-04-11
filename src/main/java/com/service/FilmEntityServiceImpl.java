package com.service;

import com.entity.FilmEntity;
import com.mapper.FilmMapper;
import com.model.PaginationResult;
import com.repository.FilmEntityRepository;
import com.kinogo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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
    public void saveFilms(List<Film> films, String language) throws Exception {
        for (Film film : films) {
            mapper.toDto(filmEntityRepository.saveFilms(mapper.toEntity(film), language));
        }
    }

    @Override
    public PaginationResult<FilmEntity> getAll(int page, int maxResult, int maxNavigationPage) {
        return filmEntityRepository.getAll(page, maxResult, maxNavigationPage);
    }

//    @Override
//    public List<FilmEntity> getAll() {
//        return filmEntityRepository.getAll();
//    }

    @Override
    public FilmEntity getFilmById(long id) {
        return filmEntityRepository.getFilmById(id);
    }

//    @Override
//    public void update(FilmEntity film) {
//        filmEntityRepository.update(film);
//    }

    @Override
    public void delete(long id) {
        filmEntityRepository.delete(id);
    }
}

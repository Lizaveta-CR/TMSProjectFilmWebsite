package com.mapper;

import com.entity.FilmEntity;
import com.FilmStorageKinogo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper extends AbstractMapper<FilmEntity, Film> {

    @Autowired
    public FilmMapper() {
        super(FilmEntity.class, Film.class);
    }
}

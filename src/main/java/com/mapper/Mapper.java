package com.mapper;

import com.entity.FilmEntity;
import com.kinogo.Film;

public interface Mapper<E extends FilmEntity, D extends Film> {
    E toEntity(D dto);

    D toDto(E entity);
}

package com.service;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.model.PaginationResult;

import java.util.List;
import java.util.Set;

public interface OrderService {
    void saveOrder(OrderEntity order);

    OrderEntity getOrderById(long id);

    List<OrderEntity> getOrdersByUsername(UserEntity userEntity);

    Set<FilmEntity> getFilmsByOrder(long orderEntityId);
}

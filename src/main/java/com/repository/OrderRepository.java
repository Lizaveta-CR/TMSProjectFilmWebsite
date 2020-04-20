package com.repository;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.model.PaginationResult;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface OrderRepository {
    void saveOrder(OrderEntity order);

    OrderEntity getOrderById(long id);

    List<OrderEntity> getOrdersByUsername(UserEntity userEntity);

    Set<FilmEntity> getFilmsByOrder(long orderEntityId);

    PaginationResult<OrderEntity> getAll(int page, int maxResult, int maxNavigationPage);

    Map<UserEntity, Double> getStatistics();
}

package com.repository;

import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.model.PaginationResult;

import java.util.List;


public interface OrderRepository {
    void saveOrder(OrderEntity order);

    PaginationResult<OrderEntity> getAll(int page, int maxResult, int maxNavigationPage);

    OrderEntity getOrderById(long id);

    List<OrderEntity> getOrdersByUsername(UserEntity userEntity);
}

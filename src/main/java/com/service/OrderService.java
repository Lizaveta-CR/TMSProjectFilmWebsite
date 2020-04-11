package com.service;

import com.entity.OrderEntity;
import com.model.PaginationResult;

public interface OrderService {
    void saveOrder(OrderEntity order);

    PaginationResult<OrderEntity> getAll(int page, int maxResult, int maxNavigationPage);

    OrderEntity getOrderById(long id);
}

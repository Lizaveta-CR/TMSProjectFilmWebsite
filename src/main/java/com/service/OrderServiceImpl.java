package com.service;

import com.entity.OrderEntity;
import com.model.PaginationResult;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void saveOrder(OrderEntity order) {
        orderRepository.saveOrder(order);
    }

    @Override
    public PaginationResult<OrderEntity> getAll(int page, int maxResult, int maxNavigationPage) {
        return orderRepository.getAll(page, maxResult, maxNavigationPage);
    }

    @Override
    public OrderEntity getOrderById(long id) {
        return orderRepository.getOrderById(id);
    }
}

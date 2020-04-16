package com.service;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.model.PaginationResult;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void saveOrder(OrderEntity order) {
        orderRepository.saveOrder(order);
    }

    @Override
    public OrderEntity getOrderById(long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public List<OrderEntity> getOrdersByUsername(UserEntity userEntity) {
        return orderRepository.getOrdersByUsername(userEntity);
    }

    @Override
    public Set<FilmEntity> getFilmsByOrder(long orderEntityId) {
        return orderRepository.getFilmsByOrder(orderEntityId);
    }
}

package com.service;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;

public interface UserService {
    void save(UserEntity user);

    UserEntity findByUsername(String username);

    UserEntity findByMobile(String mobile);

    UserEntity getUserByOrder(OrderEntity order);

}

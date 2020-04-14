package com.repository;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.entity.UserRole;

public interface UserRepository {

    UserEntity findByUserName(String username);

    UserEntity findByMobile(String mobile);

    UserEntity getUserByOrder(OrderEntity order);

    void save(UserEntity user);

    void save(UserRole role);
}
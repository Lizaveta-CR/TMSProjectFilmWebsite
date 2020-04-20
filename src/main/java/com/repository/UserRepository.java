package com.repository;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.entity.UserRole;
import com.model.PaginationResult;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    UserEntity findByUserName(String username);

    UserEntity findByMobile(String mobile);

    List<UserEntity> getAll();

    Set<UserRole> getRolesByUser(String username);

    void save(UserEntity user);

    void save(UserRole role);
}
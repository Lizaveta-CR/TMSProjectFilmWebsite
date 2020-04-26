package com.service;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.entity.UserRole;
import com.model.PaginationResult;

import java.util.List;
import java.util.Set;

public interface UserService {
    void save(UserEntity user);

    void update(UserEntity user);

    UserEntity findByUsername(String username);

    UserEntity findByMobile(String mobile);

    UserEntity findByEmail(String email);

    List<UserEntity> getAll();

    Set<UserRole> getRolesByUser(String username);

    void makeAdmin(UserEntity userEntity);

    void deleteAuthority(UserEntity byUsername);

    void deleteUser(UserEntity user);
}

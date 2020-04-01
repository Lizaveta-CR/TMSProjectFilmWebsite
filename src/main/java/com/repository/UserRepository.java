package com.repository;

import com.entity.UserEntity;
import com.entity.UserRole;

public interface UserRepository {

    UserEntity findByUserName(String username);

    UserEntity findByMobile(String mobile);

    void save(UserEntity user);

    void save(UserRole role);
}
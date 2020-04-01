package com.service;

import com.entity.UserEntity;

public interface UserService {
    void save(UserEntity user);

    UserEntity findByUsername(String username);

    UserEntity findByMobile(String mobile);
}

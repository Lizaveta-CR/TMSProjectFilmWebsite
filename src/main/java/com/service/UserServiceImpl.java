package com.service;

import com.entity.*;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(UserEntity user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setEnabled(true);
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setUser(user);
        user.getUserRole().add(userRole);
        userRepository.save(userRole);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public UserEntity findByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }
}

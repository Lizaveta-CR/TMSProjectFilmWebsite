package com.service;

import com.entity.*;
import com.model.PaginationResult;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


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

    @Override
    public List<UserEntity> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Set<UserRole> getRolesByUser(String username) {
        return userRepository.getRolesByUser(username);
    }
}

package com.repository;

import com.entity.UserEntity;
import com.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public UserEntity findByUserName(String username) {

        List<UserEntity> users = new ArrayList<UserEntity>();

        users = getSession().createQuery("from UserEntity where username=?1").setParameter(1, username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    @Override
    public UserEntity findByMobile(String mobile) {
        List<UserEntity> mobils = new ArrayList<UserEntity>();

        mobils = getSession().createQuery("from UserEntity where mobile=?1").setParameter(1, mobile)
                .list();

        if (mobils.size() > 0) {
            return mobils.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void save(UserEntity user) {
        getSession().persist(user);
    }

    @Override
    public void save(UserRole role) {
        getSession().persist(role);
    }

}
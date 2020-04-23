package com.repository;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.entity.UserRole;
import com.model.PaginationResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);
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
            logger.info("User was found by name");
            return users.get(0);
        } else {
            logger.info("User was not found by name");
            return null;
        }
    }

    @Override
    public UserEntity findByMobile(String mobile) {
        List<UserEntity> mobiles = new ArrayList<UserEntity>();

        mobiles = getSession().createQuery("from UserEntity where mobile=?1").setParameter(1, mobile)
                .list();
        if (mobiles.size() > 0) {
            logger.info("User was found by mobile");
            return mobiles.get(0);
        } else {
            logger.info("User was not found by mobile");
            return null;
        }
    }

    @Override
    public List<UserEntity> getAll() {
        String sql = "from UserEntity";
        List<UserEntity> users = getSession().createQuery(sql).list();
        logger.info("GetAllPagination Users method was successfully done");
        return users;
    }

    @Override
    public Set<UserRole> getRolesByUser(String username) {
        Set<UserRole> userRoles = new HashSet<>();

        List<UserEntity> userEntities =
                getSession().createQuery("from UserEntity where username=?1").setParameter(1, username).list();

        for (UserEntity userEntity : userEntities) {
            Set<UserRole> films = userEntity.getUserRole();
            userRoles.addAll(films);
        }
        if (userRoles.size() > 0) {
            logger.info("Roles were found by user");
            return userRoles;
        } else {
            return null;
        }
    }

    @Override
    public void save(UserEntity user) {
        getSession().persist(user);
        logger.info("User was successfully saved. User details = " + user);
    }

    @Override
    public void save(UserRole role) {
        getSession().persist(role);
        logger.info("UserRole was successfully saved. User details =" + role);
    }

    @Override
    public void update(UserEntity user) {
        getSession().saveOrUpdate(user);
        logger.info("User was successfully updated. User details =" + user);
    }

    @Override
    public void update(UserRole role) {
        getSession().saveOrUpdate(role);
        logger.info("UserRole was successfully updated. User details =" + role);
    }

    @Override
    public void deleteAuthority(UserEntity byUsername) {
        Set<UserRole> userRole = byUsername.getUserRole();
        for (UserRole role : userRole) {
            if (role.getRole().equals("ROLE_ADMIN")) {
                byUsername.removeRole(role);
                getSession().delete(role);
                getSession().update(byUsername);
            }
        }
    }
}
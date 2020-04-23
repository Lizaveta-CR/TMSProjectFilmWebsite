package com.repository;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.OrderItem;
import com.entity.UserEntity;
import com.model.PaginationResult;
import com.service.SecurityServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger logger = LogManager.getLogger(OrderRepositoryImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrder(OrderEntity order) {
        getSession().persist(order);
        logger.info("Order was saved. Order details = " + order);
    }

    @Override
    public OrderEntity getOrderById(long id) {
        OrderEntity order = (OrderEntity) getSession().get(OrderEntity.class, id);
        logger.info("Order by id was done. Order details = " + order);
        return order;
    }

    @Override
    public List<OrderEntity> getOrdersByUsername(UserEntity userEntity) {
        List<OrderEntity> orderEntities = new ArrayList<OrderEntity>();

        orderEntities = getSession().createQuery("from OrderEntity where user=?1").setParameter(1, userEntity)
                .list();

        if (orderEntities.size() > 0) {
            logger.info("Orders by username were done");
            return orderEntities;
        } else {
            return null;
        }
    }

    @Override
    public Set<FilmEntity> getFilmsByOrder(long orderEntityId) {
        Set<FilmEntity> filmEntities = new HashSet<>();

        List<OrderEntity> orderEntities =
                getSession().createQuery("from OrderEntity where order_id=?1").setParameter(1, orderEntityId).list();

        for (OrderEntity orderEntity : orderEntities) {
            Set<FilmEntity> films = orderEntity.getFilms();
            filmEntities.addAll(films);
        }
        if (filmEntities.size() > 0) {
            logger.info("Films by order were done");
            return filmEntities;
        } else {
            return null;
        }
    }

    @Override
    public PaginationResult<OrderEntity> getAll(int page, int maxResult, int maxNavigationPage) {
        Query query = getSession().createQuery("from OrderEntity");

        logger.info("GetAllPagination Orders method was successfully done");
        return new PaginationResult<OrderEntity>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public Map<UserEntity, Double> getStatistics() {
        Map<UserEntity, Double> userEntityDoubleMap = new HashMap<>();
        List<Object[]> fromDatabaseNameTotalPrice = getSession().createQuery("select o.user as name,sum ( o.price ) as totalprice from OrderEntity o group by o.user")
                .getResultList();
        for (Object[] objects : fromDatabaseNameTotalPrice) {
            userEntityDoubleMap.put((UserEntity) objects[0], (Double) objects[1]);
        }
        logger.info("Statistics was given");
        return userEntityDoubleMap;
    }
}

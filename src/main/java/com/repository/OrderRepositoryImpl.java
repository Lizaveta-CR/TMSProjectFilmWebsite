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

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger logger = LogManager.getLogger(SecurityServiceImpl.class);

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
    public PaginationResult<OrderEntity> getAll(int page, int maxResult, int maxNavigationPage) {
        String sql = "from OrderEntity ";

        Query query = getSession().createQuery(sql);

        logger.info("GetAllPagination Orders method was successfully done");
        return new PaginationResult<OrderEntity>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public OrderEntity getOrderById(long id) {
        OrderEntity order = (OrderEntity) getSession().get(OrderEntity.class, id);
        return order;
    }

    @Override
    public List<OrderEntity> getOrdersByUsername(UserEntity userEntity) {
        List<OrderEntity> orderEntities = new ArrayList<OrderEntity>();

        orderEntities = getSession().createQuery("from OrderEntity where user=?1").setParameter(1, userEntity)
                .list();

        if (orderEntities.size() > 0) {
            return orderEntities;
        } else {
            return null;
        }
    }
}

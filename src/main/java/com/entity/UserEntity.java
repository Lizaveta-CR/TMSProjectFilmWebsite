package com.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity {

    private String username;
    private String password;
    private String mobile;
    private boolean enabled;
    private Set<UserRole> userRole = new HashSet<UserRole>(0);
    private Set<OrderEntity> orders = new HashSet<>(0);

    public UserEntity() {
    }

    public UserEntity(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public UserEntity(String username, String password, boolean enabled, Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    @Id
    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    public void addOrder(OrderEntity orderEntity) {
        orders.add(orderEntity);
    }
}

package com.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity {
    private int order_id;
    private int order_num;
    private UserEntity user;
    private Set<FilmEntity> films = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int id) {
        this.order_id = id;
    }

    @Column(name = "order_num", nullable = false)
    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    public UserEntity getUser() {
        return user;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_item", joinColumns = {@JoinColumn(name = "order_id")}
            , inverseJoinColumns = {@JoinColumn(name = "film_id")})

    public Set<FilmEntity> getFilms() {
        return films;
    }

    public void setFilms(Set<FilmEntity> films) {
        this.films = films;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

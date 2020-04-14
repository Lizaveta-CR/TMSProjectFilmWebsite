package com.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    private long order_id;
    private UserEntity user;
    private Set<FilmEntity> films = new HashSet<>(0);

    public OrderEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long id) {
        this.order_id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    public UserEntity getUser() {
        return user;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
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

    public void addFilm(FilmEntity filmEntity) {
        films.add(filmEntity);
    }

    public void removeFilm(FilmEntity filmEntity) {
        films.remove(filmEntity);
    }
}

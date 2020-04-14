package com.model;

import com.entity.OrderEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserStorage {
    private  OrderEntity order;
    private String username;
    private static Set<String> filmNames = new HashSet<>(0);

    public UserStorage() {
    }

    public UserStorage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getFilmNames() {
        return filmNames;
    }

    public void setFilmNames(Set<String> filmNames) {
        this.filmNames = filmNames;
    }

    public void addFilm(String filmName) {
        filmNames.add(filmName);
    }

    public boolean isInFilms(String filmName) {
        return filmNames.contains(filmName);
    }

//    public Set<String> getFilmNamesByUserName(String username) {
//        if (username == this.username) {
//            return filmNames;
//        }
//        return null;
//    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}

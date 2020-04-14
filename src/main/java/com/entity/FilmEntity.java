package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "films")
public class FilmEntity implements Serializable {
    private long film_id;
    private String name;
    private String year;
    private String quality;
    private String translation;
    private String continuance;
    private String date;
    private String price;
    private Set<OrderEntity> orders = new HashSet<>(0);

    public FilmEntity() {
    }

    @Id
    public long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(long film_id) {
        this.film_id = film_id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(name = "quality")
    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Column(name = "translation")
    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Column(name = "continuance")
    public String getContinuance() {
        return continuance;
    }

    public void setContinuance(String continuance) {
        this.continuance = continuance;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "films")
    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    public void addOrder(OrderEntity order) {
        orders.add(order);
    }

    public void removeOrder(OrderEntity order) {
        orders.remove(order);
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FilmEntity that = (FilmEntity) o;
//        return film_id == that.film_id &&
//                Objects.equals(name, that.name) &&
//                Objects.equals(year, that.year) &&
//                Objects.equals(quality, that.quality) &&
//                Objects.equals(translation, that.translation) &&
//                Objects.equals(continuance, that.continuance) &&
//                Objects.equals(date, that.date) &&
//                Objects.equals(price, that.price) &&
//                Objects.equals(orders, that.orders);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(film_id, name, year, quality, translation, continuance, date, price, orders);
//    }
}

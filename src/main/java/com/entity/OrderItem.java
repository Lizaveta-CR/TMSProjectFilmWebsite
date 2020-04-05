package com.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
    private Long order_item_id;
    private int price;
    private OrderEntity order;
    private FilmEntity film;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(Long id) {
        this.order_item_id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false,
            foreignKey = @ForeignKey(name = "order_detail_ord_fk"))
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "film_id",
            foreignKey = @ForeignKey(name = "order_detail_ord_fk"))
    public FilmEntity getFilms() {
        return film;
    }

    public void setFilms(FilmEntity film) {
        this.film = film;
    }

    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

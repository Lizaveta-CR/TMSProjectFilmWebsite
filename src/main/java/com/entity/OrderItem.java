package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItem implements Serializable {
    private long order_id;
    private long film_id;

    public OrderItem() {
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(order_id, orderItem.order_id) &&
                Objects.equals(film_id, orderItem.film_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, film_id);
    }
}

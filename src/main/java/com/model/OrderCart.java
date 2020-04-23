package com.model;

import com.entity.FilmEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public class OrderCart {
    private double totalPrice = 0;

    public OrderCart() {
    }

    public double buildTotalPrice(Set<FilmEntity> filmEntitySet) {
        filmEntitySet.stream().forEach(filmEntity -> {
            String price = filmEntity.getPrice();
            if (price.equals("free")) {
                totalPrice = totalPrice + 0;
            } else {
                double doubleFilmPrice = Double.parseDouble(price.replace(',', '.'));
                totalPrice = totalPrice + doubleFilmPrice;
                BigDecimal totalPriceBigDecimal = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
                totalPrice = totalPriceBigDecimal.doubleValue();
            }
        });
        return totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

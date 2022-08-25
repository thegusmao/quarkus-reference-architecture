package br.com.redhat.domain;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long restaurant;
    
    public Product() {
    }
    
    public Product(String name, BigDecimal price, Long restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Long getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Long restaurant) {
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

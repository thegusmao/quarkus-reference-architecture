package br.com.redhat.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductEntity {
    @Id @GeneratedValue private Long id;
    private String name;
    private BigDecimal price;

    @ManyToOne
    private RestaurantEntity restaurant;

    public ProductEntity() {}
    
    public ProductEntity(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public void copy(ProductEntity other) {
        this.name = other.name;
        this.price = other.price;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }
    
}

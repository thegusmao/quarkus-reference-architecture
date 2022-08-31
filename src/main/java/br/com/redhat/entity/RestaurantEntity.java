package br.com.redhat.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id @GeneratedValue private Long id;
    private String name;
    private String address;
    
    @OneToMany(mappedBy = "restaurant")
    private List<ProductEntity> products;
    
    public RestaurantEntity() {}
    
    public RestaurantEntity(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}

package br.com.redhat.domain;

import java.util.List;

public class RestaurantWithProducts extends Restaurant {
    
    private List<Product> products;
    
    public RestaurantWithProducts() {}

    public RestaurantWithProducts(String name, String address, List<Product> products) {
        super(name, address);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }    
}

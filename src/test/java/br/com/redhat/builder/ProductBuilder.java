package br.com.redhat.builder;

import java.math.BigDecimal;

import br.com.redhat.domain.Product;

public class ProductBuilder {
    
    public static Product simplePojo() {
        return new Product("Hamburguer", BigDecimal.TEN, 1L);
    }  
}

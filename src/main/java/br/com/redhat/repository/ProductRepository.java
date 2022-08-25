package br.com.redhat.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.redhat.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductEntity> {
    
}

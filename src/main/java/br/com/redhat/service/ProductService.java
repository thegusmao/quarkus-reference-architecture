package br.com.redhat.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.redhat.domain.Product;
import br.com.redhat.entity.ProductEntity;
import br.com.redhat.mapper.ProductMapper;
import br.com.redhat.repository.ProductRepository;

@ApplicationScoped
public class ProductService {
    
    @Inject
    ProductMapper mapper;

    @Inject
    ProductRepository repository;

    @Transactional
    public Long create(Product produto) {
        ProductEntity entity = mapper.toEntity(produto);
        repository.persist(entity);
        return entity.getId();
    }

    public List<Product> all() {
        return mapper.toDomains(repository.listAll());
    }

    public List<Product> fromRestaurant(Long restaurantId) {
        List<ProductEntity> products = repository.find("restaurant.id", restaurantId).list();
        return mapper.toDomains(products);
    }

    public Optional<Product> withId(Long id) {
        Optional<ProductEntity> entity = repository.findByIdOptional(id);
        if(entity.isPresent()) {
            return Optional.of(mapper.toDomain(entity.get()));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Transactional
    public Optional<Product> update(Long id, Product produto) {
        Optional<ProductEntity> entity = repository.findByIdOptional(id);
        if(entity.isPresent()) {
            entity.get().copy(mapper.toEntity(produto));
            return Optional.of(mapper.toDomain(entity.get()));
        }
        return Optional.empty();
    }
}

package br.com.redhat.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.redhat.domain.Restaurant;
import br.com.redhat.domain.RestaurantWithProducts;
import br.com.redhat.entity.RestaurantEntity;
import br.com.redhat.mapper.RestaurantMapper;
import br.com.redhat.mapper.RestaurantWithProductsMapper;
import br.com.redhat.repository.RestaurantRepository;

@ApplicationScoped
public class RestaurantService {
    
    @Inject
    RestaurantMapper mapper;

    @Inject
    RestaurantWithProductsMapper completeMapper;

    @Inject
    RestaurantRepository repository;

    public List<Restaurant> all() {
        return mapper.toDomains(repository.listAll());
    }

    @Transactional
    public Long create(Restaurant restaurant) {
        RestaurantEntity entity = mapper.toEntity(restaurant);
        repository.persist(entity);

        return entity.getId();
    }


    public Optional<Restaurant> withId(Long id) {
        Optional<RestaurantEntity> entity = repository.findByIdOptional(id);
        if(entity.isPresent()) {
            return Optional.of(mapper.toDomain(entity.get()));
        }
        return Optional.empty();
    }

    public Optional<RestaurantWithProducts> withIdComplete(Long id) {
        Optional<RestaurantEntity> entity = repository.findByIdOptional(id);
        if(entity.isPresent()) {
            return Optional.of(completeMapper.toDomain(entity.get()));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Transactional
    public Optional<Restaurant> update(Long id, Restaurant restaurant) {
        Optional<RestaurantEntity> entity = repository.findByIdOptional(id);
        if(entity.isPresent()) {
            mapper.updateEntityFromDomain(restaurant, entity.get());
            return Optional.of(mapper.toDomain(entity.get()));
        }
        return Optional.empty();
    }
}

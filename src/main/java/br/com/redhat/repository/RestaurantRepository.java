package br.com.redhat.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.redhat.entity.RestaurantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class RestaurantRepository implements PanacheRepository<RestaurantEntity> {

}

package br.com.redhat.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.redhat.domain.Restaurant;
import br.com.redhat.entity.RestaurantEntity;

@Mapper(componentModel = "cdi")
public interface RestaurantMapper {
    
    RestaurantEntity toEntity(Restaurant domain);
    
    Restaurant toDomain(RestaurantEntity entity);

    List<RestaurantEntity> toEntities(List<Restaurant> domains);

    List<Restaurant> toDomains(List<RestaurantEntity> entities);
}

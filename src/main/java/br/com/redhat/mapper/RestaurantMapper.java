package br.com.redhat.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.redhat.domain.Product;
import br.com.redhat.domain.Restaurant;
import br.com.redhat.domain.RestaurantWithProducts;
import br.com.redhat.entity.ProductEntity;
import br.com.redhat.entity.RestaurantEntity;

@Mapper(componentModel = "cdi")
public interface RestaurantMapper {
    
    RestaurantEntity toEntity(Restaurant domain);
    
    Restaurant toDomain(RestaurantEntity entity);
    
    List<RestaurantEntity> toEntities(List<Restaurant> domains);
    
    List<Restaurant> toDomains(List<RestaurantEntity> entities);
    
    @Mapping( target = "id", ignore = true)
    void updateEntityFromDomain(Restaurant restaurant, @MappingTarget RestaurantEntity entity);
}

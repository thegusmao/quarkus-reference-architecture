package br.com.redhat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.redhat.domain.Product;
import br.com.redhat.domain.RestaurantWithProducts;
import br.com.redhat.entity.ProductEntity;
import br.com.redhat.entity.RestaurantEntity;

@Mapper(componentModel = "cdi")
public interface RestaurantWithProductsMapper {
    
    RestaurantWithProducts toDomain(RestaurantEntity entity);

    @Mapping(target = "restaurant", ignore = true)
    Product toDomainProduct(ProductEntity entity);
}

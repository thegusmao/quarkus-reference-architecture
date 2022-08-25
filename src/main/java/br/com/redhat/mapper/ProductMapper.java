package br.com.redhat.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.redhat.domain.Product;
import br.com.redhat.entity.ProductEntity;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    @Mapping( target = "restaurant.id", source = "restaurant" )
    ProductEntity toEntity(Product domain);
    
    @InheritInverseConfiguration
    Product toDomain(ProductEntity entity);

    List<ProductEntity> toEntities(List<Product> domains);
    
    List<Product> toDomains(List<ProductEntity> entities);
}

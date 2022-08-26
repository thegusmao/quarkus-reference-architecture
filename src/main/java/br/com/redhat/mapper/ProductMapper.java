package br.com.redhat.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.redhat.domain.Product;
import br.com.redhat.entity.ProductEntity;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    @Mapping( target = "restaurant.id", source = "restaurant" )
    ProductEntity toEntity(Product domain);
    
    @InheritInverseConfiguration(name = "toEntity")
    Product toDomain(ProductEntity entity);

    List<ProductEntity> toEntities(List<Product> domains);
    
    List<Product> toDomains(List<ProductEntity> entities);

    @InheritConfiguration(name = "toEntity")
    @Mapping( target = "id", ignore = true)
    void updateEntityFromDomain(Product product, @MappingTarget ProductEntity entity);
}

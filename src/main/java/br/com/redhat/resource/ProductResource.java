package br.com.redhat.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.redhat.domain.Product;
import br.com.redhat.service.ProductService;
import io.micrometer.core.annotation.Counted;
import io.quarkus.security.Authenticated;

@Authenticated
public class ProductResource implements ProductAPI {
    
    @Inject
    ProductService service;
    
    @Override
    public Response fromRestaurant(Long id) {
        List<Product> todos = service.fromRestaurant(id);
        return Response.ok(todos).build();
    }

    @Override
    public Response all() {
        List<Product> todos = service.all();
        return Response.ok(todos).build();
    }

    @Override
    public Response get(Long id) {
        Optional<Product> product = service.withId(id);
        if(product.isPresent()) {
            return Response.ok(product.get()).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @Override
    @Counted(value = "menu.products.created")
    public Response create(Product product) {
        Long id = service.create(product);
        return Response.created(URI.create("/products/" + id)).build();
    }

    @Override
    public Response update(Long id, Product product) {
        Optional<Product> updated = service.update(id, product);
        if(updated.isPresent()) {
            return Response.ok(updated.get()).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @Override
    public Response delete(Long id) {
        if(service.delete(id)) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }
}

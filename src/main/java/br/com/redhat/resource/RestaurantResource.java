package br.com.redhat.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.redhat.domain.Restaurant;
import br.com.redhat.domain.RestaurantWithProducts;
import br.com.redhat.service.RestaurantService;
import io.micrometer.core.annotation.Counted;
import io.quarkus.security.Authenticated;

@Authenticated
public class RestaurantResource implements RestaurantAPI {
    
    @Inject
    RestaurantService service;

    @Override
    public Response all() {
        List<Restaurant> restaurants = service.all();
        return Response.ok(restaurants).build();
    }

    @Override
    public Response get(@PathParam("id") Long id) {
        Optional<RestaurantWithProducts> restaurant = service.withIdComplete(id);
        if(restaurant.isPresent()) {
            return Response.ok(restaurant.get()).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @Override
    @Counted(value = "menu.restaurants.created")
    public Response create(Restaurant restaurant) {       
        Long id = service.create(restaurant);
        return Response.created(URI.create("/restaurants/" + id)).build();
    }

    @Override
    public Response update(@PathParam("id") Long id, Restaurant restaurant) {
        Optional<Restaurant> updated = service.update(id, restaurant);
        if(updated.isPresent()) {
            return Response.ok(updated.get()).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @Override
    public Response delete(@PathParam("id") Long id) {
        if(service.delete(id)) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }
}

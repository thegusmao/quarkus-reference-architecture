package br.com.redhat.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.redhat.domain.Restaurant;
import br.com.redhat.resource.openapi.RestaurantResourceSpec;
import br.com.redhat.service.RestaurantService;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource implements RestaurantResourceSpec {
    
    @Inject
    RestaurantService service;

    @GET
    public Response all() {
        List<Restaurant> restaurants = service.all();
        return Response.ok(restaurants).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Optional<Restaurant> restaurant = service.withId(id);
        if(restaurant.isPresent()) {
            return Response.ok(restaurant.get()).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    public Response create(Restaurant restaurant) {       
        Long id = service.create(restaurant);
        return Response.created(URI.create("/restaurants/" + id)).build();
    }
  
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Restaurant restaurant) {
        Optional<Restaurant> updated = service.update(id, restaurant);
        if(updated.isPresent()) {
            return Response.ok(updated.get()).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if(service.delete(id)) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }
}

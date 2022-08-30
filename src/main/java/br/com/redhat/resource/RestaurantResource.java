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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import br.com.redhat.domain.Restaurant;
import br.com.redhat.service.RestaurantService;
import io.micrometer.core.annotation.Counted;
import io.quarkus.security.Authenticated;

@Authenticated
@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {
    
    @Inject
    RestaurantService service;

    @Operation(summary = "Return all restaurants")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    @GET
    public Response all() {
        List<Restaurant> restaurants = service.all();
        return Response.ok(restaurants).build();
    }

    @Operation(summary = "Get specific restaurant by id")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Restaurant not found"),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Optional<Restaurant> restaurant = service.withId(id);
        if(restaurant.isPresent()) {
            return Response.ok(restaurant.get()).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @Operation(summary = "Create restaurant")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Restaurant created"),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    @POST
    @Counted(value = "menu.restaurants.created")
    public Response create(Restaurant restaurant) {       
        Long id = service.create(restaurant);
        return Response.created(URI.create("/restaurants/" + id)).build();
    }

    @Operation(summary = "Update restaurant")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Restaurant not found"),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Restaurant restaurant) {
        Optional<Restaurant> updated = service.update(id, restaurant);
        if(updated.isPresent()) {
            return Response.ok(updated.get()).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @Operation(summary = "Delete restaurant")
    @APIResponses({
        @APIResponse(responseCode = "204", description = "Restaurant deleted"),
        @APIResponse(responseCode = "404", description = "Restaurant not found"),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if(service.delete(id)) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }
}

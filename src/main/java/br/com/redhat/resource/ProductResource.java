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

import br.com.redhat.domain.Product;
import br.com.redhat.service.ProductService;
import io.micrometer.core.annotation.Counted;
import io.quarkus.security.Authenticated;

@Authenticated
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    
    @Inject
    ProductService service;
    
    @Operation(summary = "Return all products for a specific restaurant")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GET
    @Path("/restaurants/{id}")
    public Response fromRestaurant(@PathParam("id") Long id) {
        List<Product> todos = service.fromRestaurant(id);
        return Response.ok(todos).build();
    }

    @Operation(summary = "Return all products")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    @GET
    public Response all() {
        List<Product> todos = service.all();
        return Response.ok(todos).build();
    }

    @Operation(summary = "Get specific product by id")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Product not found")
    })
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Optional<Product> product = service.withId(id);
        if(product.isPresent()) {
            return Response.ok(product.get()).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @Operation(summary = "Create product")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Product created")
    })
    @POST
    @Counted(value = "menu.products.created")
    public Response create(Product product) {
        Long id = service.create(product);
        return Response.created(URI.create("/products/" + id)).build();
    }

    @Operation(summary = "Update product")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Product not found")
    })
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product product) {
        Optional<Product> updated = service.update(id, product);
        if(updated.isPresent()) {
            return Response.ok(updated.get()).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @Operation(summary = "Delete product")
    @APIResponses({
        @APIResponse(responseCode = "204", description = "Product deleted"),
        @APIResponse(responseCode = "404", description = "Product not found")
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

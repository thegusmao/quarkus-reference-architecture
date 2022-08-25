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

import br.com.redhat.domain.Product;
import br.com.redhat.resource.openapi.ProductResourceSpec;
import br.com.redhat.service.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource implements ProductResourceSpec {
    
    @Inject
    ProductService service;

    @GET
    @Path("/restaurants/{id}")
    public Response fromRestaurante(@PathParam("id") Long id) {
        List<Product> todos = service.fromRestaurant(id);
        return Response.ok(todos).build();
    }

    @GET
    public Response all() {
        List<Product> todos = service.all();
        return Response.ok(todos).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Optional<Product> product = service.withId(id);
        if(product.isPresent()) {
            return Response.ok(product.get()).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    public Response create(Product product) {
        Long id = service.create(product);
        return Response.created(URI.create("/products/" + id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product product) {
        Optional<Product> updated = service.update(id, product);
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

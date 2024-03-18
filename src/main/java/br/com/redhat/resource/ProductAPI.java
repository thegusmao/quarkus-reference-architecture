package br.com.redhat.resource;

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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import br.com.redhat.domain.Product;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProductAPI {

    @Operation(summary = "Return all products for a specific restaurant")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GET
    @Path("/restaurants/{id}")
    public Response fromRestaurant(@PathParam("id") Long id);

    @Operation(summary = "Return all products")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    @GET
    public Response all();

    @Operation(summary = "Get specific product by id")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Product not found")
    })
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id);

    @Operation(summary = "Create product")
    @APIResponses({
        @APIResponse(responseCode = "201", description = "Product created")
    })
    @POST
    public Response create(Product product);

    @Operation(summary = "Update product")
    @APIResponses({
        @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Product not found")
    })
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product product);

    @Operation(summary = "Delete product")
    @APIResponses({
        @APIResponse(responseCode = "204", description = "Product deleted"),
        @APIResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
        @APIResponse(responseCode = "404", description = "Product not found")
    })
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id);
}

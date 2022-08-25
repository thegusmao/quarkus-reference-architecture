package br.com.redhat.resource.openapi;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.redhat.domain.Product;

public interface ProductResourceSpec {
    public Response all();

    @Operation(summary = "Retorna todos os produtos cadastrados")
    @APIResponse(responseCode = "200",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(
                implementation = Product.class,
                type = SchemaType.ARRAY)))
    public Response get(Long id);

    public Response create(Product product);

    public Response update(Long id, Product product);

    public Response delete(Long id);
}

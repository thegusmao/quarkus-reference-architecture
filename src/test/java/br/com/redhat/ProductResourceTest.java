package br.com.redhat;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;

import java.net.URL;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.redhat.builder.ProductBuilder;
import br.com.redhat.builder.RestaurantBuilder;
import br.com.redhat.domain.Product;
import br.com.redhat.entity.RestaurantEntity;
import br.com.redhat.repository.RestaurantRepository;
import br.com.redhat.resource.ProductResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

// @TestHTTPEndpoint(ProductResource.class)
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// @QuarkusTest
public class ProductResourceTest {
    
    @TestHTTPEndpoint(ProductResource.class)
    @TestHTTPResource
    URL url;

    // @Test
    // @Order(1)
    public void shouldRegisterProduct() {
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(ProductBuilder.simplePojo())
        .when()
            .post()
        .then()
            .statusCode(Status.CREATED.getStatusCode())
            // .header("location", equalTo(url + "/1"))
            ;
    }

    // @Test
    // @Order(2)
    public void shouldFindProducts() {
        given()
            .accept(MediaType.APPLICATION_JSON)
        .when()
            .get()
        .then()
            .statusCode(Status.OK.getStatusCode())
            .body("size()", not(0))
            .body("name", hasItems(ProductBuilder.simplePojo().getName()));
    }

    // @Test
    // @Order(3)
    public void shouldNotFindProductWithWrongID() {
        given()
            .accept(MediaType.APPLICATION_JSON)
        .when()
            .get("/{id}", 1000L)
        .then()
            .statusCode(Status.NOT_FOUND.getStatusCode());
    }

    // @Test
    // @Order(4)
    public void shouldUpdateProductName() {
        Product product = ProductBuilder.simplePojo();
        String newName = product.getName().concat(" Jardins");
        product.setName(newName);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(product)
        .when()
            .put("/{id}", "1")
        .then()
            .statusCode(Status.OK.getStatusCode())
            .body("name", equalTo(newName))
            ;
    }

    // @Test
    // @Order(5)
    public void shouldDeleteProduct() {
        given()
        .when()
            .delete("/{id}", "1")
        .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());

        given()
        .when()
            .get("/{id}", "1")
        .then()
            .statusCode(Status.NOT_FOUND.getStatusCode());
    }
}

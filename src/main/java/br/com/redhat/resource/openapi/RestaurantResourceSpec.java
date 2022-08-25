package br.com.redhat.resource.openapi;

import javax.ws.rs.core.Response;

import br.com.redhat.domain.Restaurant;

public interface RestaurantResourceSpec {

    public Response all();

    public Response get(Long id);

    public Response create(Restaurant restaurant);

    public Response update(Long id, Restaurant restaurant);

    public Response delete(Long id);
}

package br.com.redhat.builder;

import br.com.redhat.domain.Restaurant;
import br.com.redhat.entity.RestaurantEntity;

public class RestaurantBuilder {
    
    public static Restaurant simplePojo() {
        return new Restaurant("Fogo De Chao", "Sao Paulo");
    }

    public static RestaurantEntity simpleEntity() {
        return new RestaurantEntity("Fogo De Chao", "Sao Paulo");
    }
}

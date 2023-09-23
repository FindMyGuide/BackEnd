package com.find_my_guide.main_tour_product.api.restaurantImage.dto;

import com.find_my_guide.main_tour_product.api.restaurant.domain.Restaurant;
import com.find_my_guide.main_tour_product.api.restaurantImage.domain.RestaurantImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantImageResponse {
    private Long id;
    private String imageUrl;
    private Restaurant restaurant;

    public RestaurantImageResponse(RestaurantImage restaurantImage) {
        this.id = restaurantImage.getId();
        this.imageUrl = restaurantImage.getImageUrl();
        this.restaurant = restaurantImage.getRestaurant();
    }
}

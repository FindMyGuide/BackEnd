package com.find_my_guide.main_tour_product.api.restaurantImage.domain;

import com.find_my_guide.main_tour_product.api.restaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantImage {
    @Id
    private Long id;

    private String imageUrl;

    @ManyToOne
    private Restaurant restaurant;



}

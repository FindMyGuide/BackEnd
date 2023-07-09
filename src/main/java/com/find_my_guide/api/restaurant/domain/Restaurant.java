package com.find_my_guide.api.restaurant.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    private Long id;

    private String title;
    private String address;
    private String mapx;
    private String mapy;
    private String telNo;
    private String restaurantCode;
    private String restaurantLcnc;
    private String introduce;

}

package com.find_my_guide.main_tour_product.want_tour_product.dto;

import com.find_my_guide.main_tour_product.want_tour_product.domain.Vehicle;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WantTourProductResponse {

    private Long id;
    private LocalDateTime createAt;
    private String title;
    private String content;
    private BigDecimal price;

    private Integer totalPeople;

    private Vehicle vehicle;
    private List<WantTourProductLocationResponse> locationResponses ;

    private List<LocalDate> reservationDates;


    public WantTourProductResponse(WantTourProduct wantTourProduct) {
        this.id = (wantTourProduct.getWantTourProductId() != null) ? wantTourProduct.getWantTourProductId() : 0L;
        this.createAt = (wantTourProduct.getCreatedAt() != null) ? wantTourProduct.getCreatedAt() : LocalDateTime.now();
        this.price = (wantTourProduct.getPrice() != null && wantTourProduct.getPrice().getPrice() != null) ? wantTourProduct.getPrice().getPrice() : BigDecimal.ZERO;
        this.title = (wantTourProduct.getTitle() != null && wantTourProduct.getTitle().getTitle() != null) ? wantTourProduct.getTitle().getTitle() : "";
        this.totalPeople = (wantTourProduct.getTotalPeople() != null) ? wantTourProduct.getTotalPeople() : 0;
        this.content = (wantTourProduct.getContent() != null && wantTourProduct.getContent().getContent() != null) ? wantTourProduct.getContent().getContent() : "";
        this.vehicle = (wantTourProduct.getVehicle() != null) ? wantTourProduct.getVehicle() : null;
        this.locationResponses = (wantTourProduct.getWantTourProductLocations() != null) ? wantTourProduct.getWantTourProductLocations().stream()
                .map(WantTourProductLocationResponse::new)
                .collect(Collectors.toList()) : Collections.emptyList();
        this.reservationDates = (wantTourProduct.getWantReservationDates() != null) ? wantTourProduct.getWantReservationDates().stream()
                .map(wantReservationDate -> wantReservationDate.getDate())
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}

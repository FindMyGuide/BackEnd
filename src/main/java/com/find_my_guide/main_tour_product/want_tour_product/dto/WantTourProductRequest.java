package com.find_my_guide.main_tour_product.want_tour_product.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.want_tour_product.domain.Vehicle;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WantTourProductRequest {
    private Long wantTourProductId;

    private LocalDate createAt;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private Vehicle vehicle;

    private List<Long> locationIds;


    public WantTourProduct toWantTourProduct() {
        return WantTourProduct.builder()
                .wantTourProductId(wantTourProductId)
                .createAt(createAt)
                .title(new Title(title))
                .content(new Content(content))
                .vehicle(vehicle)
                .build();
    }
}

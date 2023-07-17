package com.find_my_guide.main_tour_product.want_tour_product.domain;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WantTourProduct {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long WantTourProductId;

    private LocalDateTime createAt;

    @Embedded
    private Title title;

    @Embedded
    private Content content;



}

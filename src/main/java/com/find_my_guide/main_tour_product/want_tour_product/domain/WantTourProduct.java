package com.find_my_guide.main_tour_product.want_tour_product.domain;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WantTourProduct {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wantTourProductId;

    private LocalDate createAt;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @OneToMany(mappedBy = "wantTourProduct")
    private List<WantTourProductLocation> wantTourProductLocations = new ArrayList<>();

   public void update(Title title, Content content) {
       this.title = title;
       this.content = content;
   }




}

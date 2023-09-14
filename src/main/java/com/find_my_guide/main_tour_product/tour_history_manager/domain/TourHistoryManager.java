package com.find_my_guide.main_tour_product.tour_history_manager.domain;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.common.domain.BaseEntity;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourHistoryManager extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tourist_id")
    private Member tourist;  // 여행객

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private Member guide;



    @ManyToOne
    @JoinColumn(name = "tour_product_id")
    private TourProduct tourProduct;

    private boolean isCompleted;

    private LocalDate tourStartDate;

    private LocalDate tourEndDate;


    public void addTourProduct(TourProduct tourProduct) {
        this.tourProduct = tourProduct;
        if (!this.tourProduct.getTourHistoryManagers().contains(this)) {
            this.tourProduct.getTourHistoryManagers().add(this);
        }
    }

    public void addTourist(Member tourist) {
        this.tourist = tourist;
        if (!tourist.getTourHistoriesAsTourist().contains(this)) {
            tourist.getTourHistoriesAsTourist().add(this);
        }
    }

    public void addGuide(Member guide){
        this.guide = guide;
        if(!guide.getTourHistoriesAsGuide().contains(this)) {
            guide.getTourHistoriesAsGuide().add(this);
        }
    }

    public void completeReservation() {
        this.isCompleted = true;
    }

    public void setTourStartDate(LocalDate tourStartDate) {
        this.tourStartDate = tourStartDate;
    }

    public void setTourEndDate(LocalDate tourEndDate){
        this.tourEndDate = tourEndDate;
    }




}

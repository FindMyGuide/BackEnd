package com.find_my_guide.main_tour_product.tour_history_manager.domain;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.common.domain.BaseEntity;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
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
    private Member guide;  // 가이드
    @ManyToOne
    @JoinColumn(name = "tour_product_id")
    private TourProduct tourProduct;

    @ManyToOne
    @JoinColumn(name = "want_tour_product_id")
    private WantTourProduct wantTourProduct;

    private boolean isCompleted;

    private LocalDate tourStartDate;

    private LocalDate tourEndDate;


    public void addTourProduct(TourProduct tourProduct) {
        this.tourProduct = tourProduct;
        if (!this.tourProduct.getTourHistoryManagers().contains(this)) {
            this.tourProduct.getTourHistoryManagers().add(this);
        }
    }

    public void addWantTourProduct(WantTourProduct wantTourProduct) {
        this.wantTourProduct = wantTourProduct;
        if (!this.wantTourProduct.getTourHistoryManagers().contains(this)) {
            this.wantTourProduct.getTourHistoryManagers().add(this);
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

    public boolean getIsCompleted(){
        return this.isCompleted;
    }
    public void updateCompletionStatus() {
        if (tourEndDate != null && !isCompleted && tourEndDate.isBefore(LocalDate.now())) {
            isCompleted = true;
        }
    }


}

package com.find_my_guide.main_tour_product.tour_history_manager.domain;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourHistoryManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tourManagerHistoryId;

    @ManyToOne
    private Member member;

    @ManyToOne
    private TourProduct tourProduct;

    private String tourStartDate;
    private String tourEndDate;
    private String guideLanguage;


    public void addTourProduct(TourProduct tourProduct) {
        this.tourProduct = tourProduct;
        if (!this.tourProduct.getTourHistoryManagers().contains(this)) {
            this.tourProduct.getTourHistoryManagers().add(this);
        } else {
            throw new IllegalArgumentException("이미 존재하는 내역입니다.");
        }
    }

    public void addMember(Member member) {
        this.member = member;

        if (!this.member.getTourHistoryManagers().contains(this)) {
            this.member.getTourHistoryManagers().add(this);
        } else {
            throw new IllegalArgumentException("이미 존재하는 내역입니다.");
        }
    }

    public void update(String tourStartDate, String tourEndDate, String guideLanguage) {
        this.tourStartDate = tourStartDate;
        this.tourEndDate = tourEndDate;
        this.guideLanguage = guideLanguage;
    }
}

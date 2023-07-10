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
}

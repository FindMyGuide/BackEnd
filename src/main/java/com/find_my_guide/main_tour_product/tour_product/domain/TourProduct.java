package com.find_my_guide.main_tour_product.tour_product.domain;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.main_tour_product.common.domain.BaseEntity;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product_like.domain.TourProductLike;
import com.find_my_guide.main_tour_product.tour_product_review.domain.TourProductReview;
import com.find_my_guide.main_tour_product.tour_product_theme.domain.TourProductTheme;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class TourProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourProduct_id")
    private Long tourProductId;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Embedded
    private Price price;

    @Embedded
    private Coordinates coordinates;

    @ElementCollection(targetClass = Languages.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "tour_product_languages", joinColumns = @JoinColumn(name = "tour_product_id"))
    @Enumerated(EnumType.STRING)
    private List<Languages> languages = new ArrayList<>();

    private String location;

    @OneToMany(mappedBy = "tourProduct", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TourHistoryManager> tourHistoryManagers = new ArrayList<>();


    @OneToMany(mappedBy = "tourProduct",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TourProductTheme> tourProductThemes = new ArrayList<>();

    @OneToMany(mappedBy = "tourProduct",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TourProductReview> tourProductReviews = new ArrayList<>();

    @OneToMany(mappedBy = "tourProduct",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AvailableDate> availableDates = new ArrayList<>();

    @OneToMany(mappedBy = "tourProduct", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TourProductLike> tourProductLikes = new ArrayList<>();

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;

    }

    public void setAvailableDates(List<AvailableDate> availableDates) {
        this.availableDates = availableDates;
    }

    public List<AvailableDate> getAvailableDates() {
        if (this.availableDates == null) {
            this.availableDates = new ArrayList<>();
        }
        return this.availableDates;
    }

    public List<TourHistoryManager> getTourHistoryManagers() {
        if (this.tourHistoryManagers == null) {
            this.tourHistoryManagers = new ArrayList<>();
        }
        return this.tourHistoryManagers;
    }




}

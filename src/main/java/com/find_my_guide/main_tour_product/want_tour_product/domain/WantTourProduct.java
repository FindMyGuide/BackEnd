package com.find_my_guide.main_tour_product.want_tour_product.domain;

import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.main_tour_product.common.domain.BaseEntity;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product.domain.Price;
import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import com.find_my_guide.main_tour_product.want_tour_product_theme.domain.WantTourProductTheme;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
public class WantTourProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wantTourProductId;

    @Embedded
    private Title title;

    @Embedded
    private Price price;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Content content;

    @Enumerated(EnumType.STRING)
    private Vehicle vehicle;

    private Integer totalPeople;

    private Boolean reserved;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "wantTourProduct", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TourHistoryManager> tourHistoryManagers = new ArrayList<>();

    @OneToMany(mappedBy = "wantTourProduct", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WantTourProductLocation> wantTourProductLocations = new ArrayList<>();

    @OneToMany(mappedBy = "wantTourProduct", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WantReservationDate> wantReservationDates = new ArrayList<>();
    @OneToMany(mappedBy = "wantTourProduct", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WantTourProductTheme> wantTourProductThemes = new ArrayList<>();

    @Builder
    public WantTourProduct(Title title, Price price, Gender gender, Content content,
                           Vehicle vehicle, Member member) {
        this.title = title;
        this.price = price;
        this.gender = gender;
        this.content = content;
        this.vehicle = vehicle;
        this.member = member;
        this.wantReservationDates = new ArrayList<>();
        this.wantTourProductLocations = new ArrayList<>();
        this.wantTourProductThemes = new ArrayList<>();
    }

    public void setReserved(){
        this.reserved = true;
    }

    public void setWantReservationDates(List<WantReservationDate> wantReservationDates) {
        this.wantReservationDates = wantReservationDates;
    }

    public List<WantReservationDate> getWantReservationDates() {
        if (this.wantReservationDates == null) {
            this.wantReservationDates = new ArrayList<>();
        }
        return this.wantReservationDates;
    }

    public List<WantTourProductLocation> getWantTourProductLocations() {
        if (this.wantTourProductLocations == null) {
            return new ArrayList<>();
        }
        return this.wantTourProductLocations;
    }


    public List<TourHistoryManager> getTourHistoryManagers() {
        if (this.tourHistoryManagers == null) {
            this.tourHistoryManagers = new ArrayList<>();
        }
        return this.tourHistoryManagers;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void update(String title,
                       String content,
                       Long price,
                       Gender gender,
                       Integer totalPeople,
                       Vehicle vehicle) {
        if (title != null) {
            this.title = new Title(title);
        }
        if (content != null) {
            this.content = new Content(content);
        }
        if (price != null) {
            this.price = new Price(price);
        }
        if (gender != null) {
            this.gender = gender;
        }
        if (totalPeople != null) {
            this.totalPeople = totalPeople;
        }
        if (vehicle != null) {
            this.vehicle = vehicle;
        }
    }

    public void setWantTourProductLocations() {
        this.wantTourProductLocations = new ArrayList<>();
    }


}

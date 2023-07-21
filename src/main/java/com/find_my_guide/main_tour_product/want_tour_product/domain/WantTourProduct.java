package com.find_my_guide.main_tour_product.want_tour_product.domain;

import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.main_tour_product.common.domain.BaseEntity;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product.domain.Price;
import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import com.find_my_guide.main_tour_product.want_tour_product_theme.domain.WantTourProductTheme;
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
public class WantTourProduct extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wantTourProductId;

    @Embedded
    private Title title;

    @Embedded
    private Price price;

    @Enumerated(EnumType.STRING) //EnumType.ORDINAL : enum의 순서 값, EnumType.STRING : enum의 이름
    private Gender gender;

    @Embedded
    private Content content;

    @Enumerated(EnumType.STRING)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "wantTourProduct")
    private List<WantTourProductLocation> wantTourProductLocations = new ArrayList<>();

    @OneToMany(mappedBy = "wantTourProduct")
    private List<WantReservationDate> wantReservationDates = new ArrayList<>();

    @OneToMany(mappedBy = "wantTourProduct")
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

    public void setMember(Member member) {
        this.member = member;
    }

    public void update(Title title, Content content) {
       this.title = title;
       this.content = content;
   }




}

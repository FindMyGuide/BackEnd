package com.find_my_guide.api.festivalDetail.domain;

import com.find_my_guide.api.festival.domain.Festival;
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
public class FestivalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long festivalDetailId;

    @OneToOne
    @JoinColumn(name = "festival_id")
    private Festival festival;

    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

}

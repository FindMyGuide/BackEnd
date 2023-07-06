package com.find_my_guide.api.festivalDetail.domain;

import com.find_my_guide.api.festival.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FestivalDetail {

    @Id
    @OneToOne(mappedBy = "festival")
    @JoinColumn(name = "qna_id")
    private Festival id;

    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

}

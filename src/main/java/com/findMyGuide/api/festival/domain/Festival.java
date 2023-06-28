package com.findMyGuide.api.festival.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Festival {

    @Id
    private Long id;

    private String title;
    private String image;
    private String mapx;
    private String mapy;
}

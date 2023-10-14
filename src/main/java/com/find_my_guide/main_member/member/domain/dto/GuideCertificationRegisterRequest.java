package com.find_my_guide.main_member.member.domain.dto;

import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GuideCertificationRegisterRequest {

    private String guideCertification;

    private List<Languages> languages = new ArrayList<>();
}

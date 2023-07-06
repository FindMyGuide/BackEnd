package com.find_my_guide.api;

import com.find_my_guide.api.festival.domain.Festival;
import com.find_my_guide.api.festival.dto.FestivalRequest;
import com.find_my_guide.api.festival.repository.FestivalRepository;
import com.find_my_guide.api.festival.service.FestivalService;
import com.find_my_guide.api.festivalDetail.dto.FestivalDetailRequest;
import com.find_my_guide.api.festivalDetail.repository.FestivalDetailRepository;
import com.find_my_guide.api.festivalDetail.service.FestivalDetailService;
import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.tour_product.domain.TourProduct;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class festivalDetailTest {

    @Autowired
    @InjectMocks
    FestivalDetailService festivalDetailService;

    @Autowired
    @InjectMocks
    FestivalService festivalService;

    @Mock
    FestivalDetailRepository festivalDetailRepository;

    @Mock
    FestivalRepository festivalRepository;

    @Test
    public void getApi()  {
        // Given
        FestivalRequest festivalRequest = new FestivalRequest();
        Festival festival = festivalRequest.toFestival(267647515L, "제목", "이미지", "x좌표", "y좌표");

        Festival saveFestival = festivalRepository.save(festival);

        System.out.println(festival);
        System.out.println(festival.getId() + ", " + festival.getTitle());
        System.out.println(saveFestival);
        assertThat("제목").isEqualTo("제목");


        // When

        // Then
    }
}

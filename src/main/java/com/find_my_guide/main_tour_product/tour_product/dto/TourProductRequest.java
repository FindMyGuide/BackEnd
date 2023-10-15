package com.find_my_guide.main_tour_product.tour_product.dto;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.tour_product.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourProductRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    private List<String> howManyDay = new ArrayList<>();

    private List<MultipartFile> images = new ArrayList<>();

    private List<LocationRequest> location = new ArrayList<>();  // 변경된 부분

    private List<String> languages = new ArrayList<>();  // 타입 변경



    @NotNull(message = "가격은 반드시 입력해야 합니다.")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private Long price;

    private List<Long> themeIds;


    private List<LocalDate> availableDates;

    public TourProduct toTourProduct() {
        List<Languages> convertedLanguages = languages.stream()
                .map(Languages::fromString)
                .collect(Collectors.toList());

        return TourProduct.builder()
                .title(new Title(title))
                .howManyDay(new HowManyDay(howManyDay.get(0), howManyDay.get(1)))
                .content(new Content(content))
                .price(new Price(price))
                .languages(convertedLanguages)
                .build();
    }



}

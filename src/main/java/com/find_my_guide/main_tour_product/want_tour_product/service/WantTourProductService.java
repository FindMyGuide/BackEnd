package com.find_my_guide.main_tour_product.want_tour_product.service;

import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateRequest;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.location.dto.WantTourLocationRequest;
import com.find_my_guide.main_tour_product.location.repository.LocationRepository;
import com.find_my_guide.main_tour_product.theme.domain.Theme;
import com.find_my_guide.main_tour_product.theme.repository.ThemeRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import com.find_my_guide.main_tour_product.tour_product.domain.Price;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductRequest;
import com.find_my_guide.main_tour_product.tour_product_location.domain.TourProductLocation;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeRequest;
import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import com.find_my_guide.main_tour_product.want_reservation_date.dto.WantReservationDateRequest;
import com.find_my_guide.main_tour_product.want_reservation_date.repository.WantReservationDateRepository;
import com.find_my_guide.main_tour_product.want_reservation_date.service.WantReservationDateService;
import com.find_my_guide.main_tour_product.want_tour_product.domain.Vehicle;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product.dto.UpdateWantTourProductRequest;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductRequest;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductResponse;
import com.find_my_guide.main_tour_product.want_tour_product.repository.WantTourProductRepository;
import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationRequest;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationResponse;
import com.find_my_guide.main_tour_product.want_tour_product_location.repository.WantTourProductLocationRepository;
import com.find_my_guide.main_tour_product.want_tour_product_location.service.WantTourProductLocationService;
import com.find_my_guide.main_tour_product.want_tour_product_theme.domain.WantTourProductTheme;
import com.find_my_guide.main_tour_product.want_tour_product_theme.dto.WantTourProductThemeRequest;
import com.find_my_guide.main_tour_product.want_tour_product_theme.repository.WantTourProductThemeRepository;
import com.find_my_guide.main_tour_product.want_tour_product_theme.service.WantTourProductThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WantTourProductService {

    private final WantTourProductRepository wantTourProductRepository;
    private final WantTourProductLocationService wantTourProductLocationService;
    private final MemberRepository memberRepository;
    private final WantTourProductThemeService wantTourProductThemeService;
    private final WantReservationDateService wantReservationDateService;
    private final WantTourProductThemeRepository wantTourProductThemeRepository;

    private final TourHistoryManagerService tourHistoryManagerService;
    private final WantTourProductLocationRepository wantTourProductLocationRepository;

    private final WantReservationDateRepository wantReservationDateRepository;
    private final LocationRepository locationRepository;

    private final ThemeRepository themeRepository;

    @Transactional
    public WantTourProductResponse registerWantTourProduct(String memberId, WantTourProductRequest wantTourProductRequest) {
        WantTourProduct wantTourProduct = wantTourProductRequest.toWantTourProduct();

        if (wantTourProduct.getWantTourProductLocations() == null) {
            wantTourProduct.setWantTourProductLocations();
        }

        wantTourProduct = wantTourProductRepository.save(wantTourProduct);


        Member member = memberRepository.findByEmail(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));


        addWantThemes(wantTourProductRequest, wantTourProduct);
        addWantDates(wantTourProductRequest, wantTourProduct);

        List<WantTourLocationRequest> location = wantTourProductRequest.getLocation();

        for (WantTourLocationRequest locationRequest : location) {
            Location locationEntity = locationRequest.toLocation();
            Location savedLocation = locationRepository.save(locationEntity);

            WantTourProductLocation build = WantTourProductLocation.builder()
                    .wantTourProduct(wantTourProduct)
                    .location(savedLocation)
                    .build();

            wantTourProductLocationRepository.save(build);
        }
        wantTourProduct.setMember(member);

        WantTourProduct save = wantTourProductRepository.save(wantTourProduct);


        tourHistoryManagerService.registerTourProductByTourist(memberId, save.getWantTourProductId());

        WantTourProductResponse wantTourProductResponse = new WantTourProductResponse(save);


        List<WantTourProductLocationResponse> collect = wantTourProductLocationRepository.findByWantTourProduct_WantTourProductId(save.getWantTourProductId())
                .stream()
                .map(WantTourProductLocationResponse::new)
                .collect(Collectors.toList());


        wantTourProductResponse.setLocationResponses(collect);




        return wantTourProductResponse;
    }


    @Transactional
    public void delete(String email, Long id) {
        WantTourProduct wantTourProduct = findWantTourProductById(id);

        if (wantTourProduct.getMember().getEmail()!= email ){
            throw new IllegalArgumentException("당신이 만든 상품이 아닙니다.");
        }

        if (wantTourProduct.getReserved()==Boolean.TRUE){
            throw new IllegalArgumentException("예약된 원해요 상품이라 삭제할 수 없습니다. ");
        }
        wantTourProductRepository.delete(wantTourProduct);
    }

    public List<WantTourProductResponse> showCurrentUserWantTourProductList(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException());

        return wantTourProductRepository.findAllByMember(member)
                .stream()
                .map(WantTourProductResponse::new)
                .collect(Collectors.toList());

    }


    @Transactional
    public WantTourProductResponse update(Long wantTourProductId, UpdateWantTourProductRequest request) {
        WantTourProduct wantTourProduct = wantTourProductRepository.findById(wantTourProductId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 상품이 없습니다."));

        Gender gender = null;

        Vehicle vehicle = null;
        if (request.getVehicle() != null && !request.getVehicle().isEmpty()) {
            vehicle =Vehicle.fromString(request.getVehicle());
        }
        wantTourProduct.update(
                request.getTitle(),
                request.getContent(),
                request.getPrice(),
                gender,
                request.getTotalPeople(),
                vehicle
        );

        // 위치 업데이트 로직
        List<WantTourProductLocation> existingLocations = wantTourProductLocationRepository.findByWantTourProduct_WantTourProductId(wantTourProductId);
        wantTourProductLocationRepository.deleteAll(existingLocations);


        List<WantTourLocationRequest> location = request.getLocation();

        for (WantTourLocationRequest locationRequest : location) {
            Location locationEntity = locationRequest.toLocation();
            Location savedLocation = locationRepository.save(locationEntity);

            WantTourProductLocation build = WantTourProductLocation.builder()
                    .wantTourProduct(wantTourProduct)
                    .location(savedLocation)
                    .build();

            wantTourProductLocationRepository.save(build);
        }

// 테마 업데이트 로직
        List<WantTourProductTheme> existingThemes = wantTourProductThemeRepository.findByWantTourProduct(wantTourProduct);
        wantTourProductThemeRepository.deleteAll(existingThemes);

        if(request.getThemeIds() != null) {
            for (Long themeId : request.getThemeIds()) {
                Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 테마가 없습니다."));
                WantTourProductTheme build = WantTourProductTheme.builder()
                        .theme(theme)
                        .wantTourProduct(wantTourProduct)
                        .build();
                wantTourProductThemeRepository.save(build);
            }
        }

// 예약일 업데이트 로직
        WantReservationDate existingDate = wantReservationDateRepository.findByWantTourProductAndId(wantTourProduct, wantTourProductId);
        if (existingDate != null) {
            wantReservationDateRepository.delete(existingDate);
        }

        if(request.getWantDates() != null) {
            for (LocalDate wantDate : request.getWantDates()) {
                WantReservationDate build = WantReservationDate.builder()
                        .wantTourProduct(wantTourProduct)
                        .date(wantDate)
                        .build();
                wantReservationDateRepository.save(build);
            }
        }

        return new WantTourProductResponse(wantTourProduct);
    }


    public List<WantTourProductResponse> showAllWantTourProductList() {
        return wantTourProductRepository.findAll().stream()
                .map(WantTourProductResponse::new)
                .collect(Collectors.toList());
    }

    public WantTourProductResponse showWantTourProductList(Long id) {
        return new WantTourProductResponse(findWantTourProductById(id));
    }


    private WantTourProduct findWantTourProductById(Long id) {
        return wantTourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }


    private void addWantThemes(WantTourProductRequest wantTourProductRequest, WantTourProduct wantTourProduct) {
        for (Long themeId : wantTourProductRequest.getThemeIds()) {
            WantTourProductThemeRequest wantTourProductThemeRequest = new WantTourProductThemeRequest(themeId, wantTourProduct.getWantTourProductId());
            wantTourProductThemeService.addThemeToWantTourProduct(wantTourProductThemeRequest);
        }
    }

    private void addWantDates(WantTourProductRequest wantTourProductRequest, WantTourProduct wantTourProduct) {
        for (LocalDate wantDate : wantTourProductRequest.getWantDates()) {
            WantReservationDateRequest wantReservationDateRequest = new WantReservationDateRequest(wantDate);
            wantReservationDateService.registerDate(wantTourProduct.getWantTourProductId(), wantReservationDateRequest);
        }
    }


}

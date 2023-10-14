package com.find_my_guide.main_tour_product.tour_product.service;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateRequest;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
import com.find_my_guide.main_tour_product.available_reservation_date.service.AvailableService;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.location.repository.LocationRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import com.find_my_guide.main_tour_product.tour_product.domain.Images;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductRequest;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product.repository.ImagesRepository;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
import com.find_my_guide.main_tour_product.tour_product_location.domain.TourProductLocation;
import com.find_my_guide.main_tour_product.tour_product_location.dto.TourProductLocationResponse;
import com.find_my_guide.main_tour_product.tour_product_location.repository.TourProductLocationRepository;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeRequest;
import com.find_my_guide.main_tour_product.tour_product_theme.service.TourProductThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourProductService {

    private final TourProductRepository tourProductRepository;

    private final TourProductThemeService tourProductThemeService;

    private final TourProductLocationRepository tourProductLocationRepository;

    private final AvailableService availableService;

    private final LocationRepository locationRepository;

    private final TourProductLikeRepository tourProductLikeRepository;

    private final TourHistoryManagerService tourHistoryManagerService;

    private final MemberRepository memberRepository;

    private final ImagesRepository imagesRepository;

    @Transactional
    public TourProductResponse registerTourProduct(String email, TourProductRequest tourProductRequest) {
        TourProduct tourProduct = tourProductRequest.toTourProduct();

        if (tourProduct.getTourProductLocations() == null) {
            tourProduct.setTourProductLocations();
        }



        tourProduct = tourProductRepository.save(tourProduct);

        List<Images> savedImagesList = new ArrayList<>();
        List<Images> imagesList = tourProductRequest.getImages();
        for (Images image : imagesList) {
            image.setTourProduct(tourProduct);
            Images savedImage = imagesRepository.save(image);
            savedImagesList.add(savedImage);
        }

        Member member = findMember(email);

        tourProduct.setImages(savedImagesList);

        addTheme(tourProductRequest, tourProduct);

        addDates(tourProductRequest, tourProduct);

        List<LocationRequest> locations = tourProductRequest.getLocation();

        for (LocationRequest locationRequest : locations) {
            Location locationEntity = locationRequest.toLocation();
            Location savedLocation = locationRepository.save(locationEntity);

            TourProductLocation build = TourProductLocation.builder()
                    .tourProduct(tourProduct)
                    .location(savedLocation)
                    .build();
            tourProductLocationRepository.save(build);
        }

        TourProduct save = tourProductRepository.save(tourProduct);


        tourHistoryManagerService.registerTourProductByGuide(member.getEmail(), save.getTourProductId());

        TourProductResponse tourProductResponse = new TourProductResponse(save);

        tourProductResponse.setThemeResponses(tourProductThemeService.findByTourProductId(save.getTourProductId()));

        tourProductResponse.setLocations(findByTourProductId(save.getTourProductId()));

        return tourProductResponse;
    }


    @Transactional
    public TourProductResponse update(Long id, TourProductRequest tourProductRequest) {
        TourProduct tourProduct = findById(id);
        tourProduct.update(new Title(tourProductRequest.getTitle()), new Content(tourProductRequest.getContent()));
        return new TourProductResponse(tourProductRepository.save(tourProduct));

    }

    @Transactional
    public TourProductResponse delete(Long postId) {
        TourProduct tourProduct = findById(postId);
        tourProductRepository.delete(tourProduct);
        return new TourProductResponse(tourProduct);

    }

    public List<TourProductResponse> showTourProductList() {
        return tourProductRepository.findAll().stream().map(TourProductResponse::new).collect(Collectors.toList());
    }

    public List<TourProductResponse> showTourProductListInOrderLikes() {
        List<TourProduct> tourProducts = tourProductRepository.findAll();
        Collections.sort(tourProducts, Comparator.comparingLong(tourProduct -> countLikes(tourProduct.getTourProductId())));

        return tourProducts.stream().map(TourProductResponse::new).collect(Collectors.toList());
    }

    public List<AvailableDateResponse> availableDates(Long postId) {
        TourProduct tourProduct = findById(postId);

        return tourProduct.getAvailableDates().stream().map(AvailableDateResponse::new).collect(Collectors.toList());
    }


    public TourProductResponse detail(Long id) {
        return new TourProductResponse(findById(id));
    }


    public long countLikes(Long tourProductId) {
        return tourProductLikeRepository.countByTourProduct_TourProductId(tourProductId);
    }


    public List<TourProductLocationResponse> findByTourProductId(Long id){
        return tourProductLocationRepository.findByTourProduct_TourProductId(id)
                .stream()
                .map(TourProductLocationResponse::new)
                .collect(Collectors.toList());

    }

    public List<TourProductResponse> getTourProductsByLocationTitle(String title) {
        return tourProductLocationRepository.findTourProductsByLocationTitle(new Title(title))
                .stream()
                .map(TourProductResponse::new)
                .collect(Collectors.toList());
    }

    public List<TourProductResponse> getTourProductsContainingLocationTitle(String title) {
        return tourProductLocationRepository.findTourProductsByContainingLocationTitle(title)
                .stream()
                .map(TourProductResponse::new)
                .collect(Collectors.toList());
    }

    public List<TourProductResponse> findAllProductsByTheme(String theme){
        return tourProductRepository.findByThemeTitleContaining(theme)
                .stream()
                .map(TourProductResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Images registerImage (Images images){
        return imagesRepository.save(images);
    }

    private TourProduct findById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 관광 상품 입니다."));
    }


    private void addDates(TourProductRequest tourProductRequest, TourProduct tourProduct) {
        for (LocalDate availableDate : tourProductRequest.getAvailableDates()) {
            AvailableDateRequest availableDateRequest = new AvailableDateRequest(availableDate);
            availableService.registerDate(tourProduct.getTourProductId(), availableDateRequest);
        }
    }

    private void addTheme(TourProductRequest tourProductRequest, TourProduct tourProduct) {
        for (Long themeId : tourProductRequest.getThemeIds()) {
            TourProductThemeRequest tourProductThemeRequest = new TourProductThemeRequest(themeId, tourProduct.getTourProductId());
            tourProductThemeService.addThemeToTourProduct(tourProductThemeRequest);
        }
    }

    private Member findMember(String memberId) {
        return memberRepository.findByEmail(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 회원"));
    }

}

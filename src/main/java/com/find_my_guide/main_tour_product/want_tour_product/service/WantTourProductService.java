package com.find_my_guide.main_tour_product.want_tour_product.service;

import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductRequest;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductResponse;
import com.find_my_guide.main_tour_product.want_tour_product.repository.WantTourProductRepository;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationRequest;
import com.find_my_guide.main_tour_product.want_tour_product_location.service.WantTourProductLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WantTourProductService {

    private final WantTourProductRepository wantTourProductRepository;
    private final WantTourProductLocationService wantTourProductLocationService;
    private final MemberRepository memberRepository;

    @Transactional
    public WantTourProductResponse registerWantTourProduct(Long memberId, WantTourProductRequest wantTourProductRequest) {
        WantTourProduct wantTourProduct = wantTourProductRequest.toWantTourProduct();
        wantTourProduct = wantTourProductRepository.save(wantTourProduct);
        addLocation(wantTourProductRequest, wantTourProduct);


        return new WantTourProductResponse(wantTourProduct);
    }

    @Transactional
    public WantTourProductResponse update(Long id, WantTourProductRequest wantTourProductRequest) {
        WantTourProduct wantTourProduct = findWantTourProductById(id);
        wantTourProduct.update(new Title(wantTourProductRequest.getTitle()), new Content(wantTourProductRequest.getContent()));

        return new WantTourProductResponse(wantTourProductRepository.save(wantTourProduct));
    }

    @Transactional
    public WantTourProductResponse delete(Long id) {
        WantTourProduct wantTourProduct = findWantTourProductById(id);
        wantTourProductRepository.delete(wantTourProduct);
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


    private void addLocation(WantTourProductRequest wantTourProductRequest, WantTourProduct wantTourProduct) {
        for (Long locationId : wantTourProductRequest.getLocationIds()) {
            WantTourProductLocationRequest wantTourProductLocationRequest =
                    new WantTourProductLocationRequest(locationId, wantTourProduct.getWantTourProductId());
            wantTourProductLocationService.addLocationToWantTourProduct(wantTourProductLocationRequest);
        }
    }
}

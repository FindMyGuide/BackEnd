package com.find_my_guide.main_tour_product.want_tour_product.controller;

import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductRequest;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductResponse;
import com.find_my_guide.main_tour_product.want_tour_product.service.WantTourProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WantTourProductController {

    private final WantTourProductService wantTourProductService;

    @PostMapping("/wantTourProduct/register/{memberId}")
    public ResponseEntity<WantTourProductResponse> addWantTourProduct(
            @PathVariable Long memberId,
            @RequestBody WantTourProductRequest wantTourProductRequest) {
        WantTourProductResponse wantTourProductResponse =
                wantTourProductService.registerWantTourProduct(memberId, wantTourProductRequest);
        return ResponseEntity.ok(wantTourProductResponse);
    }

    @GetMapping("/wantTourProduct/{wantTourProductId}")
    public ResponseEntity<WantTourProductResponse> detailWantTourProduct(
            @Valid @PathVariable Long wantTourProductId
    ) {
        WantTourProductResponse wantTourProductResponse = wantTourProductService.showWantTourProductList(wantTourProductId);
        return ResponseEntity.ok(wantTourProductResponse);
    }

    @GetMapping("/wantTourProducts")
    public ResponseEntity<List<WantTourProductResponse>> AllWantTourProduct() {
        List<WantTourProductResponse> wantTourProductResponses = wantTourProductService.showAllWantTourProductList();
        return ResponseEntity.ok(wantTourProductResponses);
    }

}

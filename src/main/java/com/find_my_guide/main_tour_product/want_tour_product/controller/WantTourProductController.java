package com.find_my_guide.main_tour_product.want_tour_product.controller;

import com.find_my_guide.main_tour_product.want_tour_product.dto.UpdateWantTourProductRequest;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductRequest;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductResponse;
import com.find_my_guide.main_tour_product.want_tour_product.service.WantTourProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class WantTourProductController {

    private final WantTourProductService wantTourProductService;

    @PostMapping("/want-tourProduct/register")
    public ResponseEntity<WantTourProductResponse> addWantTourProduct(
            final Authentication authentication,
            @RequestBody WantTourProductRequest wantTourProductRequest) {

        WantTourProductResponse wantTourProductResponse =
                wantTourProductService.registerWantTourProduct((String) authentication.getPrincipal(), wantTourProductRequest);
        return ResponseEntity.ok(wantTourProductResponse);
    }

    @GetMapping("/want-tourProduct/{wantTourProductId}")
    public ResponseEntity<WantTourProductResponse> detailWantTourProduct(
            @Valid @PathVariable Long wantTourProductId
    ) {
        WantTourProductResponse wantTourProductResponse = wantTourProductService.showWantTourProductList(wantTourProductId);
        return ResponseEntity.ok(wantTourProductResponse);
    }

    @GetMapping("/want-tourProducts")
    public ResponseEntity<List<WantTourProductResponse>> AllWantTourProduct() {
        List<WantTourProductResponse> wantTourProductResponses = wantTourProductService.showAllWantTourProductList();
        return ResponseEntity.ok(wantTourProductResponses);
    }

    @GetMapping("/want-tourProduct/login-user")
    public ResponseEntity<List<WantTourProductResponse>> getAllCurrentUserWantTourProducts(final Authentication authentication) {
        return ResponseEntity.ok(wantTourProductService.showCurrentUserWantTourProductList((String) authentication.getPrincipal()));
    }

    @PutMapping("/want-tourProduct/{id}")
    public ResponseEntity<WantTourProductResponse> update(@PathVariable Long id, @RequestBody UpdateWantTourProductRequest request) {
        WantTourProductResponse response = wantTourProductService.update(id,request);
        return ResponseEntity.ok(response);
    }

}

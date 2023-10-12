package com.find_my_guide.main_tour_product.tour_history_manager.repository;

import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.tour_product.domain.Images;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class TourProductDummyDataGenerator {

    private static final Random random = new Random();

    public TourProduct generateRandomTourProduct() {
        String title = "Tour" + random.nextInt(100);
        String content = "This is a content for tour: " + title;
        List<String> howManyDay = Arrays.asList("1박", "2일");
        List<Images> images = new ArrayList<>();
        List<LocationRequest> location = new ArrayList<>();
        List<Languages> languages = Arrays.asList(Languages.values());
        Long price = (long) random.nextInt(200) + 100;
        List<Long> themeIds = new ArrayList<>();
        List<LocalDate> availableDates = Arrays.asList(LocalDate.now().plusDays(random.nextInt(10)));

        TourProductRequest request = new TourProductRequest(title, content, howManyDay, images, location, languages, price, themeIds, availableDates);

        return request.toTourProduct();
    }
}

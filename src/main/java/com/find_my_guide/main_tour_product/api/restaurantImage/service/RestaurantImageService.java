package com.find_my_guide.main_tour_product.api.restaurantImage.service;

import com.find_my_guide.main_tour_product.api.restaurant.domain.Restaurant;
import com.find_my_guide.main_tour_product.api.restaurant.dto.RestaurantRequest;
import com.find_my_guide.main_tour_product.api.restaurant.dto.RestaurantResponse;
import com.find_my_guide.main_tour_product.api.restaurant.repository.RestaurantRepository;
import com.find_my_guide.main_tour_product.api.restaurantImage.dto.RestaurantImageRequest;
import com.find_my_guide.main_tour_product.api.restaurantImage.dto.RestaurantImageResponse;
import com.find_my_guide.main_tour_product.api.restaurantImage.repository.RestaurantImageRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantImageService {

    private final RestaurantImageRepository restaurantImageRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public String getApi() {
        String result;

        try {
            URL url = new URL("https://busan-7beach.openapi.redtable.global/api/food/img?serviceKey=tLrTsrUteIbenzAV9bAVR6zv01p69xPrtGdx9pu9Vl4GOey9XJlkXFFCZuNM5Xur");
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String response = bf.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response);

            JSONArray body = (JSONArray) jsonObject.get("body");
            for (Object o : body) {
                JSONObject item = (JSONObject) o;
                parseRestaurantImageRequest(item);
            }

            result = "성공적";
        } catch (Exception e) {
            result = "실패했습니다.";
            System.out.println(e);
        }
        return result;
    }

    @Transactional
    public void save(RestaurantImageRequest restaurantImageRequest) {
        Long id = restaurantImageRequest.getId();
        if (restaurantImageRepository.existsById(id)) {
            System.out.println("A restaurant with id " + id + " already exists. Skipping...");
            return;
        }

        restaurantImageRepository.save(restaurantImageRequest.toRestaurantImage());
        System.out.println("저장완료");
    }

    public List<RestaurantImageResponse> restaurantImageResponses(){
        return restaurantImageRepository.findAll()
                .stream()
                .map(RestaurantImageResponse::new)
                .collect(Collectors.toList());
    }

    private void parseRestaurantImageRequest(JSONObject item) {
        Long id = Long.parseLong(getValueOrEmpty(item, "MENU_ID"));
        String imageUrl = getValueOrEmpty(item, "FOOD_IMG_URL");
        Long restaurantId = Long.parseLong(getValueOrEmpty(item, "RSTR_ID"));

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isEmpty()) {
            return;
        }
        RestaurantImageRequest restaurantImageRequest = new RestaurantImageRequest(id, imageUrl, restaurant.get());
        save(restaurantImageRequest);
    }

    private String getValueOrEmpty(JSONObject jsonObject, String key) {
        return jsonObject.get(key) != null ? jsonObject.get(key).toString() : "";
    }
}

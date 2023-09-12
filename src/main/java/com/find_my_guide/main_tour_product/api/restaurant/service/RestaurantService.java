package com.find_my_guide.main_tour_product.api.restaurant.service;

import com.find_my_guide.main_tour_product.api.restaurant.dto.RestaurantRequest;
import com.find_my_guide.main_tour_product.api.restaurant.dto.RestaurantResponse;
import com.find_my_guide.main_tour_product.api.restaurant.repository.RestaurantRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public String getApi() {
        String result;

        try {
            URL url = new URL("https://busan-7beach.openapi.redtable.global/api/rstr?serviceKey=tLrTsrUteIbenzAV9bAVR6zv01p69xPrtGdx9pu9Vl4GOey9XJlkXFFCZuNM5Xur");
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String response = bf.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response);

            JSONArray body = (JSONArray) jsonObject.get("body");
            for (Object o : body) {
                JSONObject item = (JSONObject) o;
                RestaurantRequest restaurantRequest = parseRestaurantRequest(item);
                save(restaurantRequest);
            }

            result = "성공적";
        } catch (Exception e) {
            result = "실패했습니다.";
            System.out.println(e);
        }
        return result;
    }

    @Transactional
    public void save(RestaurantRequest restaurantRequest) {
        Long id = restaurantRequest.getId();
        if (restaurantRepository.existsById(id)) {
            System.out.println("A restaurant with id " + id + " already exists. Skipping...");
            return;
        }

        restaurantRepository.save(restaurantRequest.toRestaurant());
        System.out.println("저장완료");
    }

    public List<RestaurantResponse> restaurantResponses(){
        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantResponse::new)
                .collect(Collectors.toList());
    }

    private RestaurantRequest parseRestaurantRequest(JSONObject item) {
        Long id = Long.parseLong(getValueOrEmpty(item, "RSTR_ID"));
        String title = getValueOrEmpty(item, "RSTR_NM");
        String address = getValueOrEmpty(item, "RSTR_RDNMADR");
        String mapx = getValueOrEmpty(item, "RSTR_LA");
        String mapy = getValueOrEmpty(item, "RSTR_LO");
        String telNo = getValueOrEmpty(item, "RSTR_TELNO");
        String restaurantCode = getValueOrEmpty(item, "BSNS_STATM_BZCND_NM");
        String restaurantLcnc = getValueOrEmpty(item, "BSNS_LCNC_NM");
        String introduce = getValueOrEmpty(item, "RSTR_INTRCN_CONT");

        return new RestaurantRequest(id, title, address, mapx, mapy, telNo,
                restaurantCode, restaurantLcnc, introduce);
    }

    private String getValueOrEmpty(JSONObject jsonObject, String key) {
        return jsonObject.get(key) != null ? jsonObject.get(key).toString() : "";
    }

}

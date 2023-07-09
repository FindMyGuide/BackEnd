package com.find_my_guide.main_tour_product.api.restaurant.service;

import com.find_my_guide.main_tour_product.api.restaurant.dto.RestaurantRequest;
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

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(),
                    "UTF-8"));
            result = bf.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONArray body = (JSONArray) jsonObject.get("body");

            for (int i = 0; i < body.size(); i++) {
                JSONObject items = (JSONObject) body.get(i);

                Long id = 0L;
                if (items.get("RSTR_ID") != null) {
                    id = Long.valueOf(items.get("RSTR_ID").toString()); // 식당 ID
                } else  {
                    System.out.println("error!");
                }

                String title = "";
                if (items.get("RSTR_NM") != null) {
                    title = items.get("RSTR_NM").toString(); // 식당명
                } else  {
                    System.out.println("error!");
                }

                String address = "";
                if (items.get("RSTR_RDNMADR") != null){
                    address = items.get("RSTR_RDNMADR").toString(); // 식당 주소
                } else  {
                    System.out.println("error!");
                }

                String mapx = "";
                if (items.get("RSTR_LA") != null){
                    mapx = items.get("RSTR_LA").toString(); // 식당 위도
                } else  {
                    System.out.println("error!");
                }

                String mapy = "";
                if (items.get("RSTR_LO") != null){
                    mapy = items.get("RSTR_LO").toString();// 식장 경도
                } else  {
                    System.out.println("error!");
                }

                String telNo = "";
                if (items.get("RSTR_TELNO") != null){
                    telNo = items.get("RSTR_TELNO").toString(); // 식당대표전화번호
                } else  {
                    System.out.println("error!");
                }

                String restaurantCode = "";
                if (items.get("BSNS_STATM_BZCND_NM") != null){
                    restaurantCode = items.get("BSNS_STATM_BZCND_NM").toString(); // 영업신고증업태명
                } else  {
                    System.out.println("error!");
                }

                String restaurantLcnc = "";
                if (items.get("BSNS_LCNC_NM") != null){
                    restaurantLcnc = items.get("BSNS_LCNC_NM").toString(); // 영업인 허가명
                } else  {
                    System.out.println("error!");
                }

                String introduce = "";
                if (items.get("RSTR_INTRCN_CONT") != null){
                    introduce = items.get("RSTR_INTRCN_CONT").toString();; // 음식점 소개 내용
                } else  {
                    System.out.println("error!");
                }


                RestaurantRequest restaurantRequest = new RestaurantRequest();

                save(restaurantRequest, id, title, address, mapx, mapy, telNo,
                        restaurantCode, restaurantLcnc, introduce);
            }

            result = "성공적";
        } catch (Exception e) {
            result = "실패했습니다.";
            System.out.println(e);
        }
        return result;
    }

    @Transactional
    public void save(RestaurantRequest restaurantRequest, Long id, String title, String address,
                     String mapx, String mapy, String telNo,
                     String restaurantCode, String restaurantLcnc,
                     String introduce) {

        restaurantRepository.save(restaurantRequest.toRestaurant(id, title, address,
                mapx, mapy, telNo, restaurantCode, restaurantLcnc, introduce));
        System.out.println("저장완료");
    }
}

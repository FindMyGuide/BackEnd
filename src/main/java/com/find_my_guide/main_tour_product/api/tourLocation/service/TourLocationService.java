package com.find_my_guide.main_tour_product.api.tourLocation.service;

import com.find_my_guide.main_tour_product.api.tourLocation.dto.TourLocationRequest;
import com.find_my_guide.main_tour_product.api.tourLocation.repository.TourLocationRepository;
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
public class TourLocationService {

    private  final TourLocationRepository tourLocationRepository;

    @Transactional
    public String getApi() {
        String result;

        try {
            for (int i = 1; i < 16; i++) {
                URL url = new URL("https://apis.data.go.kr/B551011/KorService1/areaBasedSyncList1" +
                        "?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D" +
                        "&numOfRows=500&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&showflag=1&listYN=Y&arrange=A&contentTypeId=12" +
                        "&areaCode=6&sigunguCode=" + i);
                System.out.println(url.toString());

                BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(),
                        "UTF-8"));

                result = bf.readLine();
                System.out.println(result);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(result);
                System.out.println(jsonObject);
                JSONObject response = (JSONObject) jsonObject.get("response");
                JSONObject body = (JSONObject) response.get("body");
                System.out.println(body);
                JSONObject items = (JSONObject) body.get("items");
                System.out.println(items);
                JSONArray item = (JSONArray) items.get("item");
                System.out.println(item);


                for (int j = 0; j < item.size(); j++) {
                    JSONObject location = (JSONObject) item.get(j);

                    System.out.println(location);

                    Long id = Long.valueOf(location.get("contentid").toString());
                    String title = location.get("title").toString();
                    String image = location.get("firstimage").toString();
                    String mapx = location.get("mapx").toString();
                    String mapy = location.get("mapy").toString();


                    save(id, title, image, mapx, mapy);
                }

            }




            result = "성공적";
        } catch (Exception e) {
            result = "실패";
        }


        return result;
    }

    @Transactional
    public void save(Long id, String title,
                     String image, String mapx, String mapy) {
        System.out.println("저장");
        TourLocationRequest tourLocationRequest = new TourLocationRequest(id, title, image, mapx, mapy);
        tourLocationRepository.save(tourLocationRequest.toTourLocation());
    }

}
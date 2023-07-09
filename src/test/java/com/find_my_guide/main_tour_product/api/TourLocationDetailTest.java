package com.find_my_guide.main_tour_product.api;

import com.find_my_guide.main_tour_product.api.tourLocation.domain.TourLocation;
import com.find_my_guide.main_tour_product.api.tourLocation.dto.TourLocationRequest;
import com.find_my_guide.main_tour_product.api.tourLocation.repository.TourLocationRepository;
import com.find_my_guide.main_tour_product.api.tourLocationDetail.domain.TourLocationDetail;
import com.find_my_guide.main_tour_product.api.tourLocationDetail.dto.TourLocationDetailRequest;
import com.find_my_guide.main_tour_product.api.tourLocationDetail.repository.TourLocationDetailRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@SpringBootTest
@RunWith(SpringRunner.class)
@Rollback(true)
public class TourLocationDetailTest {

    @Autowired
    TourLocationRepository tourLocationRepository;

    @Autowired
    TourLocationDetailRepository tourLocationDetailRepository;

    @Test
    public void getApi() {

        TourLocationRequest tourLocationRequest = new TourLocationRequest(2715601L, "제목", "이미지", "x좌표", "y좌표");
        TourLocation tourLocation = tourLocationRequest.toTourLocation();
        System.out.println(tourLocation.getTourLocationDetail());
        TourLocation saveTourLocation = tourLocationRepository.save(tourLocation);

        String result = null;

        try {
            URL url = new URL("https://apis.data.go.kr/B551011/KorService1/detailIntro1?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&" +
                    "contentId=" + saveTourLocation.getId() + "&contentTypeId=12&numOfRows=10&pageNo=1");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) jsonObject1.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");

            JSONObject contents = (JSONObject) item.get(0);

            Long id = (Long) contents.get("contentId");
            String infoCenter = contents.get("infocenter").toString();
            String restDate = contents.get("restdate").toString();
            String useTime = contents.get("usetime").toString();
            String parking = contents.get("parking").toString();

            url = new URL("https://apis.data.go.kr/B551011/KorService1/detailInfo1?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=" + saveTourLocation.getId() + "&contentTypeId=12&numOfRows=10&pageNo=1");

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(result);
            JSONObject response1 = (JSONObject) response.get("response");
            JSONObject body2 = (JSONObject) response1.get("body");
            JSONObject items2 = (JSONObject) body2.get("items");
            JSONArray item2 = (JSONArray) items2.get("item");

            JSONObject content2 = (JSONObject) item2.get(0);

            String infoText = content2.get("infotext").toString();



            TourLocationDetailRequest tourLocationDetailRequest = new TourLocationDetailRequest(infoCenter, restDate, useTime, parking, infoText);
            TourLocationDetail tourLocationDetail = tourLocationDetailRequest.toTourLocationDetail();
            tourLocationDetail.setTourLocation(saveTourLocation);

            TourLocationDetail saveTourLocationDetail = tourLocationDetailRepository.save(tourLocationDetail);


            System.out.println(saveTourLocationDetail);
        } catch (Exception e) {
            System.out.println(result);
        }


    }
}

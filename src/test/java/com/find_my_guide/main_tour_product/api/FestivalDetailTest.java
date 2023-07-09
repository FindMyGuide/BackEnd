package com.find_my_guide.main_tour_product.api;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import com.find_my_guide.main_tour_product.api.festival.dto.FestivalRequest;
import com.find_my_guide.main_tour_product.api.festival.repository.FestivalRepository;
import com.find_my_guide.main_tour_product.api.festival.service.FestivalService;
import com.find_my_guide.main_tour_product.api.festivalDetail.domain.FestivalDetail;
import com.find_my_guide.main_tour_product.api.festivalDetail.dto.FestivalDetailRequest;
import com.find_my_guide.main_tour_product.api.festivalDetail.repository.FestivalDetailRepository;
import com.find_my_guide.main_tour_product.api.festivalDetail.service.FestivalDetailService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
public class FestivalDetailTest {

    @Autowired
    @InjectMocks
    FestivalDetailService festivalDetailService;

    @Autowired
    @InjectMocks
    FestivalService festivalService;

    @Autowired
    FestivalDetailRepository festivalDetailRepository;

    @Autowired
    FestivalRepository festivalRepository;

    @Test
    public void festivalDetailGetApi(){
        FestivalRequest festivalRequest = new FestivalRequest(2674675L, "제목", "이미지", "x좌표", "y좌표");
        Festival festival = festivalRequest.toFestival();
        Festival saveFestival = festivalRepository.save(festival);

        String result = null;

        try {
            URL url = new URL("https://apis.data.go.kr/B551011/KorService1/detailIntro1?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId="+
                    saveFestival.getId() +"&contentTypeId=15&numOfRows=10&pageNo=1");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");

            JSONObject contents = (JSONObject) item.get(0);

            Long id = (Long) contents.get("contentId");
            String place = contents.get("eventplace").toString();
            String playtime = contents.get("playtime").toString();
            String startDate = contents.get("eventstartdate").toString();
            String expense = contents.get("usetimefestival").toString();

            url = new URL("https://apis.data.go.kr/B551011/KorService1/detailInfo1?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&" +
                    "MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=" + saveFestival.getId() +
                    "&contentTypeId=15&numOfRows=10&pageNo=1");

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            parser = new JSONParser();
            JSONObject response1 = (JSONObject) parser.parse(result);
            JSONObject response2 = (JSONObject) response1.get("response");
            JSONObject body2 = (JSONObject) response2.get("body");
            JSONObject items2 = (JSONObject) body2.get("items");
            JSONArray item2 = (JSONArray) items2.get("item");

            JSONObject content2 = (JSONObject) item2.get(0);

            String content = content2.get("infotext").toString();


            FestivalDetailRequest festivalDetailRequest = new FestivalDetailRequest(place, playtime,
                    startDate, expense, content);

            FestivalDetail festivalDetail = festivalDetailRequest.toFestivalDetail();
            festivalDetail.setFestival(saveFestival);

            FestivalDetail saveFestivalDetail = festivalDetailRepository.save(festivalDetail);

            System.out.println(saveFestivalDetail.getFestivalDetailId());
            System.out.println(saveFestivalDetail.getFestival());
            System.out.println(saveFestivalDetail.getPlace());
            System.out.println(saveFestivalDetail.getPlaytime());
            System.out.println(saveFestivalDetail.getStartDate());
            System.out.println(saveFestivalDetail.getExpense());
            System.out.println(saveFestivalDetail.getContent());

            Assertions.assertNotNull(saveFestivalDetail);

        } catch (Exception e) {
            System.out.println("실패");
            System.out.println(result);
            Assertions.assertTrue(false);
        }

    }

    @Test
    public void TourLocationDetailGetApi() {

    }

}

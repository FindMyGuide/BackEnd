package com.find_my_guide.api.festivalDetail.service;

import com.find_my_guide.api.festival.domain.Festival;
import com.find_my_guide.api.festival.repository.FestivalRepository;
import com.find_my_guide.api.festivalDetail.domain.FestivalDetail;
import com.find_my_guide.api.festivalDetail.dto.FestivalDetailRequest;
import com.find_my_guide.api.festivalDetail.repository.FestivalDetailRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FestivalDetailService {

    private final FestivalDetailRepository festivalDetailRepository;
    private final FestivalRepository festivalRepository;

    public String getApi(String festivalId) {
        String result;

        try {
            URL url = new URL("https://apis.data.go.kr/B551011/KorService1/detailIntro1?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId="+
                    festivalId +"&contentTypeId=15&numOfRows=10&pageNo=1");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONObject body = (JSONObject) jsonObject.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");

            JSONObject contents = (JSONObject) item.get(0);

            Long id = (Long) contents.get("contentId");
            String place = contents.get("eventplace").toString();
            String playtime = contents.get("playtime").toString();
            String startDate = contents.get("eventstartdate").toString();
            String expense = contents.get("usetimefestival").toString();

            url = new URL("https://apis.data.go.kr/B551011/KorService1/detailInfo1?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&" +
                    "MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=" + festivalId +
                    "&contentTypeId=15&numOfRows=10&pageNo=1");

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(result);
            JSONObject body2 = (JSONObject) response.get("body");
            JSONObject items2 = (JSONObject) body2.get("items");
            JSONArray item2 = (JSONArray) items2.get("item");

            JSONObject content2 = (JSONObject) item2.get(0);

            String content = content2.get("infotext").toString();


            save(id, place, playtime, startDate, expense, content);


            result = "성공적";
        } catch (Exception e) {
            result = "실패";
        }

        return result;
    }

    public void save(Long id, String place, String playtime, String startDate,
                     String expense, String content) {

        Festival festival = findFestivalById(id);

        FestivalDetailRequest festivalDetailRequest = new FestivalDetailRequest(place, playtime,
                startDate, expense, content);

        FestivalDetail festivalDetail = festivalDetailRequest.toFestivalDetail();
        festivalDetail.setFestival(festival);

        festivalDetailRepository.save(festivalDetail);
        System.out.println("저장완료");
    }

    private Festival findFestivalById(Long id) {
        return festivalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 Festival입니다."));
    }
}

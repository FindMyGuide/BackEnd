package com.find_my_guide.main_tour_product.api.festivalDetail.service;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import com.find_my_guide.main_tour_product.api.festival.repository.FestivalRepository;
import com.find_my_guide.main_tour_product.api.festivalDetail.domain.FestivalDetail;
import com.find_my_guide.main_tour_product.api.festivalDetail.dto.FestivalDetailRequest;
import com.find_my_guide.main_tour_product.api.festivalDetail.dto.FestivalDetailResponse;
import com.find_my_guide.main_tour_product.api.festivalDetail.repository.FestivalDetailRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FestivalDetailService {

    private final FestivalDetailRepository festivalDetailRepository;
    private final FestivalRepository festivalRepository;

    @Transactional
    public void allGetApi() {
        List<Festival> festivals = festivalRepository.findAll();
        for (Festival festival :
                festivals) {
            getApi(festival.getId());
        }
    }

    @Transactional
    public FestivalDetailResponse getApi(Long festivalId) {
        String result = null;

        try {
            URL url = new URL("https://apis.data.go.kr/B551011/KorService1/detailIntro1?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId="+
                    festivalId +"&contentTypeId=15&numOfRows=10&pageNo=1");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");

            JSONObject contents = (JSONObject) item.get(0);

            Long id = Long.valueOf(festivalId);
            String place = String.valueOf(contents.get("eventplace"));
            String playtime = String.valueOf(contents.get("playtime"));
            String startDate = String.valueOf(contents.get("eventstartdate"));
            String endDate = String.valueOf(contents.get("eventenddate"));
            String expense = String.valueOf(contents.get("usetimefestival"));

            url = new URL("https://apis.data.go.kr/B551011/KorService1/detailInfo1?serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&" +
                    "MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=" + festivalId +
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

            String content = String.valueOf(content2.get("infotext"));


            return save(festivalId, place, playtime, startDate, endDate, expense, content);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public FestivalDetailResponse save(Long id, String place, String playtime, String startDate, String endDate,
                     String expense, String content) {

        Festival festival = findFestivalById(id);

        return new FestivalDetailResponse(festival, place, playtime, startDate, endDate, expense, content);
    }

    private Festival findFestivalById(Long id) {
        return festivalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 Festival입니다."));
    }

    public Boolean checkFestivalEnd(Long id) {
        FestivalDetail festivalDetail = festivalDetailRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않습니다."));
        LocalDate date = Year.of(Integer.parseInt(festivalDetail.getEndDate().substring(0, 4)))
                .atMonth(Integer.parseInt(festivalDetail.getEndDate().substring(4, 6)))
                .atDay(Integer.parseInt(festivalDetail.getEndDate().substring(6, 8)));
        return !date.isBefore(LocalDate.now());
    }

    public Boolean checkProgress(Long id) {
        FestivalDetail festivalDetail = festivalDetailRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않습니다."));
        LocalDate date = Year.of(Integer.parseInt(festivalDetail.getStartDate().substring(0, 4)))
                .atMonth(Integer.parseInt(festivalDetail.getStartDate().substring(4, 6)))
                .atDay(Integer.parseInt(festivalDetail.getStartDate().substring(6, 8)));
        return date.isBefore(LocalDate.now());
    }

    public FestivalDetail findFestivalDetailById(Long id) {
        return festivalDetailRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않습니다."));
    }
}

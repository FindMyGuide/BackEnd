package com.find_my_guide.main_tour_product.api.festival.service;

import com.find_my_guide.main_tour_product.api.festival.dto.FestivalRequest;
import com.find_my_guide.main_tour_product.api.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FestivalService {

    private final FestivalRepository festivalRepository;

    private static final String SERVICE_URL = "https://apis.data.go.kr/B551011/KorService1/areaBasedSyncList1?" +
            "serviceKey=wSM4T5mSUOxzHeEO2Xi1llabPQIFXqpy5CjEWgBGdXJy%2BebCPvBQmHOPYOxcGlZMMew5yeuyfCYa9pyW7Hr0jQ%3D%3D&" +
            "numOfRows=500&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&showflag=1&listYN=Y&arrange=A&contentTypeId=15&" +
            "areaCode=6&sigunguCode=";

    @Transactional
    public List<String> getApi() {
        List<String> results = new ArrayList<>();

        IntStream.rangeClosed(1, 15).forEach(sigunguCode -> {
            try {
                results.add(fetchAndSaveFestivalData(sigunguCode));
            } catch (Exception e) {
                results.add("실패: " + e.getMessage());
            }
        });

        return results;
    }


    private String fetchAndSaveFestivalData(int sigunguCode) throws Exception {
        URL url = new URL(SERVICE_URL + sigunguCode);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept-Charset", "UTF-8");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
        }

        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String result = bf.readLine();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(result);
        JSONObject response = getObjectOrNull(jsonObject, "response");
        if (response == null) {
            throw new RuntimeException("Invalid response: no 'response' field");
        }

        JSONObject body = getObjectOrNull(response, "body");
        if (body == null) {
            throw new RuntimeException("Invalid response: no 'body' field");
        }

        JSONObject items = getObjectOrNull(body, "items");
        if (items == null) {
            throw new RuntimeException("Invalid response: no 'items' field");
        }

        JSONArray item = getArrayOrNull(items, "item");
        if (item == null) {
            throw new RuntimeException("Invalid response: no 'item' array");
        }

        item.forEach(o -> {
            JSONObject location = (JSONObject) o;
            saveFestivalData(location);
        });

        return "성공적";
    }

    private JSONObject getObjectOrNull(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {
            return (JSONObject) jsonObject.get(key);
        } else {
            return null;
        }
    }

    private JSONArray getArrayOrNull(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {
            return (JSONArray) jsonObject.get(key);
        } else {
            return null;
        }
    }

    @Transactional
    public void saveFestivalData(JSONObject location) {
        Long id = Long.valueOf(location.get("contentid").toString());
        String title = location.get("title").toString();
        String image = location.get("firstimage").toString();
        String mapX = location.get("mapx").toString();
        String mapY = location.get("mapy").toString();

        FestivalRequest festivalRequest = new FestivalRequest(id, title, image, mapX, mapY);
        festivalRepository.save(festivalRequest.toFestival());
    }
}

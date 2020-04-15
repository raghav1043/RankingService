package rankingService.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScorePredictor {

    @Autowired
    SortHotel sortHotel;

    //version 2
    public List<String> getScore2(String model, HashMap<String, List<Double>> map) {
        String uri = "http://127.0.0.1:5000/scores";
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("model", model);
        requestMap.put("data",map);
        JSONObject jsonObject = new JSONObject(requestMap);

        //System.out.println(jsonObject);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
        String response  = restTemplate.exchange(uri, HttpMethod.POST, request, String.class).getBody();
        //System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String,Double> mapResponse = mapper.readValue(response, Map.class);
            return sortHotel.sortHotelsOnScore(mapResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }


//    public List<String> getScore(String model, List<String> hotels, List<List<Double>> data)  {
//        String uri = "http://127.0.0.1:5000/scores";
//        Map<String,Object> map = new HashMap<>();
//        map.put("model", model);
//        map.put("hotels",hotels);
//        map.put("data", data);
//        JSONObject jsonObject = new JSONObject(map);
//
//        //System.out.println(jsonObject);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
//        String response  = restTemplate.exchange(uri, HttpMethod.POST, request, String.class).getBody();
//        //System.out.println(response);
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Map<String,Double> mapResponse = mapper.readValue(response, Map.class);
//            return sortHotel.sortHotelsOnScore(mapResponse);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return hotels;
//
//    }


}

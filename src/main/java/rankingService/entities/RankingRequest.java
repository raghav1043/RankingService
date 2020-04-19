package rankingService.entities;

import java.util.List;

public class RankingRequest {
    private String experimentId;
    private String userId;
    private String requestId;
    private int is_city_search;
    private int is_locality_search;
    private int is_nearby_search;
    private List<String> hotels;

    public RankingRequest(){
    }

    public RankingRequest(String experimentId, String userId, String requestId, int is_city_search, int is_locality_search,
                          int is_nearby_search, List<String> hotels) {
        this.experimentId = experimentId;
        this.userId = userId;
        this.requestId = requestId;
        this.is_city_search = is_city_search;
        this.is_locality_search = is_locality_search;
        this.is_nearby_search = is_nearby_search;
        this.hotels = hotels;
    }

    public String getExperimentId() {
        return experimentId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRequestId() {
        return requestId;
    }

    public int getIs_city_search() {
        return is_city_search;
    }

    public int getIs_locality_search() {
        return is_locality_search;
    }

    public int getIs_nearby_search() {
        return is_nearby_search;
    }

    public List<String> getHotels() {
        return hotels;
    }

}

package rankingService.request;

import java.util.List;

public class RankingRequest {
    public String experimentId;
    public String userId;
    public String requestId;
    public int is_city_search;
    public int is_locality_search;
    public int is_nearby_search;
    public List<String> hotels;

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
}

package rankingService.repository;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;
import rankingService.config.ElasticConfig;
import rankingService.model.HotelData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElasticHotelService {

    public List<HotelData> getAllHotels() throws IOException {
        ElasticConfig elasticConfig = new ElasticConfig();
        JestClient client = elasticConfig.jestClient();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(10000);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("hotelsinfo")
                .build();
        SearchResult searchResult = client.execute(search);
        List<SearchResult.Hit<HotelData, Void>> searchHits = searchResult.getHits(HotelData.class);
        List<HotelData> results = searchHits.stream()
                .map(h -> h.source)
                .collect(Collectors.toList());
        client.close();
        return results;
    }


    public HotelData getHotelEs(String hotelId){
        HotelData hotelData = null;
        try {
            ElasticConfig elasticConfig = new ElasticConfig();
            JestClient client = elasticConfig.jestClient();
            hotelData = client.execute(new Get.Builder("hotelsinfo",hotelId).type("hotels").build())
                    .getSourceAsObject(HotelData.class);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hotelData;
    }

    public List<HotelData> mgetHotelsEs(List<String> list){
        String queryDSL = "{"+
                " \"query\":{"+
                " \"terms\":"+
                "{ \"hotelId\": [";
        for(String x:list){
            queryDSL = queryDSL+"\""+x+"\",";
        }
        queryDSL = queryDSL.substring(0,queryDSL.length()-1);
        queryDSL=queryDSL+"]}}}";

        System.out.println(queryDSL);

        ElasticConfig elasticConfig = new ElasticConfig();
        JestClient client = elasticConfig.jestClient();
        List<HotelData> response = new ArrayList<>();
        try {
            JestResult result = client.execute(new Search.Builder(queryDSL).build());
            response = result.getSourceAsObjectList(HotelData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }



}

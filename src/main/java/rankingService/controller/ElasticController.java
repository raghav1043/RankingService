package rankingService.controller;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import org.springframework.web.bind.annotation.*;
import rankingService.config.ElasticConfig;
import rankingService.entities.HotelData;

import java.io.IOException;

@RestController
@RequestMapping("/elastic")
public class ElasticController {
    ElasticConfig elasticConfig = new ElasticConfig();
    JestClient jestClient = elasticConfig.jestClient();

    @GetMapping("/hotel/{id}")
    public HotelData findHotel(@PathVariable String id) throws IOException {
        HotelData getResult = jestClient.execute(new Get.Builder("hotelsinfo",id).type("hotels").build())
                .getSourceAsObject(HotelData.class);
        return getResult;
    }

    @PostMapping("/hotel")
    public String addHotel(@RequestBody HotelData hotel) throws IOException {
        System.out.println(hotel.toString());
        JestResult checkIndex = jestClient.execute(new IndicesExists.Builder("hotelsinfo").build());
        if(!checkIndex.isSucceeded()){
            jestClient.execute(new CreateIndex.Builder("hotelsinfo").build());
        }
        JestResult result = jestClient.execute(new Index.Builder(hotel).index("hotelsinfo").type("hotels").id(hotel.getHotelId()).build());
        if(result.isSucceeded()){
            return "Success";
        }else{
            return "Failed";
        }
    }

    @DeleteMapping("/hotel/{id}")
    public String deleteHotel(@PathVariable String id) throws IOException {
        JestResult result  = jestClient.execute(new Delete.Builder(id).index("hotelsinfo").type("hotels").build());
        if(result.isSucceeded()){
            return "Success";
        }else{
            return "Failed";
        }
    }

}

package rankingService.cornjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rankingService.model.HotelData;
import rankingService.repository.ElasticHotelService;
import rankingService.repository.RedisHotelService;

import java.io.IOException;
import java.util.List;

@Component
public class JobSchedular {

    @Autowired
    ElasticHotelService elasticHotelService;

    @Autowired
    RedisHotelService redisHotelService;

    @Scheduled(fixedRate = 60000)
    public void Job() throws IOException {
        System.out.println("Cron Job Running");

        List<HotelData> hotelData = elasticHotelService.getAllHotels();
        for(HotelData x : hotelData)
            redisHotelService.saveHotel(x);
    }

}

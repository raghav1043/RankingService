package rankingService.cornjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rankingService.entities.HotelData;
import rankingService.repository.ElasticHotelService;
import rankingService.repository.RedisHotelService;

import java.util.List;

@Component
public class JobSchedular {

    @Autowired
    ElasticHotelService elasticHotelService;

    @Autowired
    RedisHotelService redisHotelService;

    @Scheduled(fixedRate = 60000)
    public void Job() {
        System.out.println("Cron Job Running");

        List<HotelData> hotelDataList = elasticHotelService.getAllHotels();
        if(hotelDataList.size()!=0){
            redisHotelService.saveAll(hotelDataList);
        }

        System.out.println("Cron Job Completed");
    }

}

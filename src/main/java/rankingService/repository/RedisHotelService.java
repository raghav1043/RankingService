package rankingService.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rankingService.model.HotelData;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisHotelService {

    @Autowired
    HotelRepository hotelRepository;

    public void saveHotel(HotelData hotel){
        if(!exists(hotel.hotelId)){
            hotelRepository.save(hotel);
        }
    }

    public boolean exists(String id){
        return hotelRepository.exists(id);
    }

    public HotelData getHotel(String id){
        HotelData hotel = hotelRepository.findOne(id);
        return hotel;
    }

    public List<HotelData> getAllHotels(){
        List<HotelData> list = new ArrayList<>();
        list = (List<HotelData>) hotelRepository.findAll();
        return list;
    }

    public Iterable<HotelData> mgetHotels(List<String> hotels){
        return hotelRepository.findAll(hotels);
    }

    public void deleteAllHotels(){
        hotelRepository.deleteAll();
    }

    public void deleteHotel(String hotelId){
        hotelRepository.delete(hotelId);
    }

}

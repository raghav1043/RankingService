package rankingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rankingService.model.HotelData;
import rankingService.repository.HotelRepository;

import java.util.List;

@RestController
public class RedisController {

    @Autowired
    HotelRepository hotelRepository;

    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    public List<HotelData> getAllHotels(){
        List<HotelData> list = (List<HotelData>) hotelRepository.findAll();
        return list;
    }

    @RequestMapping(value = "/redis/{id}",method = RequestMethod.GET)
    public boolean hotelExists(@PathVariable String id){
        return hotelRepository.exists(id);
    }

    @RequestMapping(value = "/redis",method = RequestMethod.DELETE)
    public void DeleteAllHotels() throws DataAccessException {
        hotelRepository.deleteAll();
    }

}

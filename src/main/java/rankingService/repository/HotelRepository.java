package rankingService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rankingService.entities.HotelData;

@Repository
public interface HotelRepository extends CrudRepository<HotelData,String> {
}

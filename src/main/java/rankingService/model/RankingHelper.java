package rankingService.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rankingService.repository.ElasticHotelService;
import rankingService.repository.RedisHotelService;
import rankingService.request.RankingRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class RankingHelper {

    @Autowired
    RedisHotelService redisHotelService;

    @Autowired
    ScorePredictor scorePredictor;

    @Autowired
    UserData userData;

    @Autowired
    ElasticHotelService elasticHotelService;


    public List<String> getSortedHotels(RankingRequest request){
        String experimentId = request.experimentId;

        // selected features list  and model based on experimentId
        List<String> selectedFeatures = ExperimentDecider.getSelectedFeatures(experimentId);
        String model = ExperimentDecider.getSelectedModel(experimentId);

        // request dataset for flask application
        HashMap<String,List<Double>> map = new HashMap<>();

        //fetch all data from redis
        Iterable<HotelData> result = redisHotelService.mgetHotels(request.hotels);
        Iterator<HotelData> iter = result.iterator();
        while(iter.hasNext()){

            //fetch hotel properties from redis
            HotelData hotelData = iter.next();

            //construct datatset for given hotel
            FeaturesAggregator featuresAggregator = new FeaturesAggregator(hotelData.hotelId,(double)hotelData.fprice,(double)hotelData.distance,
                    (double)hotelData.hotelType, (double)hotelData.place_popularity_score,(double)hotelData.ratingCount,(double)hotelData.ratingMean,
                    (double)hotelData.discount_per, (double)hotelData.hotel_br, (double)hotelData.hotel_ctr,(double)hotelData.hotel_dtob,
                    (double)hotelData.hotel_btod,(double)hotelData.hotel_placeid_ctr, (double)hotelData.hotel_placeid_br, (double)hotelData.hotel_placeid_dtob,
                    (double)hotelData.avg_hotel_rr,(double)hotelData.avg_hotel_br, (double)hotelData.avg_hotel_ctr,(double)hotelData.avg_hotel_btod,
                    (double)hotelData.abp_price_diff,(double) hotelData.recom_score, (double)request.is_nearby_search,(double)request.is_locality_search,
                    (double)request.is_city_search,(double)hotelData.vicinity, userData.hotel_user_br, userData.hotel_user_ctr,userData.hotel_user_btod,
                    userData.user_cat_ctr,userData.user_cat_ctr,userData.user_cat_dtob,userData.avg_user_rr, userData.user_abp,
                    userData.stayLength,userData.is_weekend_checkin,userData.day_of_week_checkin,userData.is_weekend_searchDate,userData.day_of_week_searchDate,
                    userData.advPurchaseWindow
            );

            // add selected features value to list
            List<Double> selectedFeaturesValues = new ArrayList<>();
            for(int j=0;j<selectedFeatures.size();j++){
                selectedFeaturesValues.add(featuresAggregator.getValue(selectedFeatures.get(j)));
            }
            map.put(hotelData.hotelId,selectedFeaturesValues);
        }

        //------------------------------collect data from elastic search ---------------------------------------------------------------

        if(map.size()!=request.hotels.size()){
            //list of hotels not present in redis
            List<String> remainingHotels = new ArrayList<>();
            for(int i=0;i<request.hotels.size();i++){
                if(!map.containsKey(request.hotels.get(i))){
                    remainingHotels.add(request.hotels.get(i));
                }
            }

            //System.out.println("remaining Hotels");
            //System.out.println(remainingHotels);

            //fetch remaining hotels from elastic search
            List<HotelData> hotelResponse = elasticHotelService.mgetHotelsEs(remainingHotels);

            //System.out.println(hotelResponse);

            for(int i=0;i<hotelResponse.size();i++){
                //fetch hotel properties from elastic search response
                HotelData hotelData = hotelResponse.get(i);

                //construct datatset for given hotel
                FeaturesAggregator featuresAggregator = new FeaturesAggregator(hotelData.hotelId,(double)hotelData.fprice,(double)hotelData.distance,
                        (double)hotelData.hotelType, (double)hotelData.place_popularity_score,(double)hotelData.ratingCount,(double)hotelData.ratingMean,
                        (double)hotelData.discount_per, (double)hotelData.hotel_br, (double)hotelData.hotel_ctr,(double)hotelData.hotel_dtob,
                        (double)hotelData.hotel_btod,(double)hotelData.hotel_placeid_ctr, (double)hotelData.hotel_placeid_br, (double)hotelData.hotel_placeid_dtob,
                        (double)hotelData.avg_hotel_rr,(double)hotelData.avg_hotel_br, (double)hotelData.avg_hotel_ctr,(double)hotelData.avg_hotel_btod,
                        (double)hotelData.abp_price_diff,(double) hotelData.recom_score, (double)request.is_nearby_search,(double)request.is_locality_search,
                        (double)request.is_city_search,(double)hotelData.vicinity, userData.hotel_user_br, userData.hotel_user_ctr,userData.hotel_user_btod,
                        userData.user_cat_ctr,userData.user_cat_ctr,userData.user_cat_dtob,userData.avg_user_rr, userData.user_abp,
                        userData.stayLength,userData.is_weekend_checkin,userData.day_of_week_checkin,userData.is_weekend_searchDate,userData.day_of_week_searchDate,
                        userData.advPurchaseWindow
                );

                // add selected features value to list
                List<Double> selectedFeaturesValues = new ArrayList<>();
                for(int j=0;j<selectedFeatures.size();j++){
                    selectedFeaturesValues.add(featuresAggregator.getValue(selectedFeatures.get(j)));
                }
                map.put(hotelData.hotelId,selectedFeaturesValues);
            }
        }
        List<String> sortedHotels = scorePredictor.getScore2(model,map);

        return sortedHotels;
    }








//    public List<String> getSortedHotelId(RankingRequest request)  {
//        String experimentId = request.experimentId;
//
//        // selected features list  and model based on experimentId
//        List<String> selectedFeatures = ExperimentDecider.getSelectedFeatures(experimentId);
//        String model = ExperimentDecider.getSelectedModel(experimentId);
//
//        // List of hotels provided to ranking service for each request
//        List<String> hotelList = request.hotels;
//
//        //preprocessed dataset for python application
//        List<List<Double>> selectedFeaturesDataset = new ArrayList<>();
//
//        //check hotel is in redis
//        //if not add hotel in redis from elastic search
//        for(int i=0;i<hotelList.size();i++){
//            String hotelId = hotelList.get(i);
//            List<Double> selectedFeatureValueForHotel = new ArrayList<>();
//            HotelData hotelData = null;
//            if(!redisHotelService.exists(hotelId)){
//                hotelData = elasticHotelService.getHotelEs(hotelId);
//                System.out.println(hotelData.toString());
//                redisHotelService.saveHotel(hotelData);
//
//            }else{
//                hotelData = redisHotelService.getHotel(hotelId);
//            }
//
//            FeaturesAggregator featuresAggregator = new FeaturesAggregator(hotelData.hotelId,(double)hotelData.fprice,(double)hotelData.distance,
//                    (double)hotelData.hotelType, (double)hotelData.place_popularity_score,(double)hotelData.ratingCount,(double)hotelData.ratingMean,
//                    (double)hotelData.discount_per, (double)hotelData.hotel_br, (double)hotelData.hotel_ctr,(double)hotelData.hotel_dtob,
//                    (double)hotelData.hotel_btod,(double)hotelData.hotel_placeid_ctr, (double)hotelData.hotel_placeid_br, (double)hotelData.hotel_placeid_dtob,
//                    (double)hotelData.avg_hotel_rr,(double)hotelData.avg_hotel_br, (double)hotelData.avg_hotel_ctr,(double)hotelData.avg_hotel_btod,
//                    (double)hotelData.abp_price_diff,(double) hotelData.recom_score, (double)request.is_nearby_search,(double)request.is_locality_search,
//                    (double)request.is_city_search,(double)hotelData.vicinity, userData.hotel_user_br, userData.hotel_user_ctr,userData.hotel_user_btod,
//                    userData.user_cat_ctr,userData.user_cat_ctr,userData.user_cat_dtob,userData.avg_user_rr, userData.user_abp,
//                    userData.stayLength,userData.is_weekend_checkin,userData.day_of_week_checkin,userData.is_weekend_searchDate,userData.day_of_week_searchDate,
//                    userData.advPurchaseWindow
//            );
//
//            // add selected features value to list
//            for(int j=0;j<selectedFeatures.size();j++){
//                selectedFeatureValueForHotel.add(featuresAggregator.getValue(selectedFeatures.get(j)));
//            }
//
//            // add current hotel features to dataset
//            selectedFeaturesDataset.add(selectedFeatureValueForHotel);
//        }
//
//        List<String> sortedHotels = scorePredictor.getScore(model,hotelList,selectedFeaturesDataset);
//
//
//        return sortedHotels;
//    }
}

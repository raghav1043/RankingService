package rankingService.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rankingService.entities.HotelData;
import rankingService.entities.RankingRequest;
import rankingService.entities.UserData;
import rankingService.repository.ElasticHotelService;
import rankingService.repository.RedisHotelService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class FeaturesAggregator {

    @Autowired
    RedisHotelService redisHotelService;

    @Autowired
    UserData userData;

    @Autowired
    ElasticHotelService elasticHotelService;

    private String hotelId;
    private Double fprice;
    private Double distance;
    private Double hotelType;
    private Double place_popularity_score;
    private Double ratingCount;
    private Double ratingMean;
    private Double discount_per;

    private Double hotel_br;
    private Double hotel_ctr;
    private Double hotel_dtob;
    private Double hotel_btod;

    private Double hotel_placeid_ctr;
    private Double hotel_placeid_br;
    private Double hotel_placeid_dtob;

    private Double avg_hotel_rr;
    private Double avg_hotel_br;
    private Double avg_hotel_ctr;
    private Double avg_hotel_btod;

    private Double abp_price_diff;
    private Double recom_score;
    private Double vicinity;

    //request dependent features
    private Double is_nearby_search;
    private Double is_locality_search;
    private Double is_city_search;

    //user dependent features
    private Double hotel_user_br;
    private Double hotel_user_ctr ;
    private Double hotel_user_btod;

    private Double user_cat_ctr;
    private Double user_cat_br;
    private Double user_cat_dtob ;

    private Double avg_user_rr ;
    private Double user_abp ;
    private Double stayLength ;
    private Double is_weekend_checkin;
    private Double day_of_week_checkin;
    private Double is_weekend_searchDate;
    private Double day_of_week_searchDate;
    private Double advPurchaseWindow ;

    public FeaturesAggregator(){}

    public FeaturesAggregator(String hotelId, Double fprice, Double distance, Double hotelType, Double place_popularity_score,
                              Double ratingCount, Double ratingMean, Double discount_per, Double hotel_br, Double hotel_ctr,
                              Double hotel_dtob, Double hotel_btod, Double hotel_placeid_ctr, Double hotel_placeid_br,
                              Double hotel_placeid_dtob, Double avg_hotel_rr, Double avg_hotel_br, Double avg_hotel_ctr,
                              Double avg_hotel_btod, Double abp_price_diff, Double recom_score, Double is_nearby_search,
                              Double is_locality_search, Double is_city_search, Double vicinity, Double hotel_user_br,
                              Double hotel_user_ctr, Double hotel_user_btod, Double user_cat_ctr, Double user_cat_br,
                              Double user_cat_dtob, Double avg_user_rr, Double user_abp, Double stayLength, Double is_weekend_checkin,
                              Double day_of_week_checkin, Double is_weekend_searchDate, Double day_of_week_searchDate,
                              Double advPurchaseWindow) {
        this.hotelId = hotelId;
        this.fprice = fprice;
        this.distance = distance;
        this.hotelType = hotelType;
        this.place_popularity_score = place_popularity_score;
        this.ratingCount = ratingCount;
        this.ratingMean = ratingMean;
        this.discount_per = discount_per;
        this.hotel_br = hotel_br;
        this.hotel_ctr = hotel_ctr;
        this.hotel_dtob = hotel_dtob;
        this.hotel_btod = hotel_btod;
        this.hotel_placeid_ctr = hotel_placeid_ctr;
        this.hotel_placeid_br = hotel_placeid_br;
        this.hotel_placeid_dtob = hotel_placeid_dtob;
        this.avg_hotel_rr = avg_hotel_rr;
        this.avg_hotel_br = avg_hotel_br;
        this.avg_hotel_ctr = avg_hotel_ctr;
        this.avg_hotel_btod = avg_hotel_btod;
        this.abp_price_diff = abp_price_diff;
        this.recom_score = recom_score;
        this.is_nearby_search = is_nearby_search;
        this.is_locality_search = is_locality_search;
        this.is_city_search = is_city_search;
        this.vicinity = vicinity;
        this.hotel_user_br = hotel_user_br;
        this.hotel_user_ctr = hotel_user_ctr;
        this.hotel_user_btod = hotel_user_btod;
        this.user_cat_ctr = user_cat_ctr;
        this.user_cat_br = user_cat_br;
        this.user_cat_dtob = user_cat_dtob;
        this.avg_user_rr = avg_user_rr;
        this.user_abp = user_abp;
        this.stayLength = stayLength;
        this.is_weekend_checkin = is_weekend_checkin;
        this.day_of_week_checkin = day_of_week_checkin;
        this.is_weekend_searchDate = is_weekend_searchDate;
        this.day_of_week_searchDate = day_of_week_searchDate;
        this.advPurchaseWindow = advPurchaseWindow;
    }

    public String getHotelId(){return this.hotelId;}


    public Double getValue(String var){
        if(var.equals("fprice")){ return this.fprice;}
        else if(var.equals("distance")){ return this.distance;}
        else if(var.equals("hotelType")){ return this.hotelType;}
        else if(var.equals("place_popularity_score")){ return this.place_popularity_score;}
        else if(var.equals("ratingCount")){ return this.ratingCount;}
        else if(var.equals("ratingMean")){ return this.ratingMean;}
        else if(var.equals("discount_per")){ return this.discount_per;}
        else if(var.equals("hotel_br")){return this.hotel_br; }
        else if(var.equals("hotel_ctr")){ return this.hotel_ctr;}
        else if(var.equals("hotel_btod")){return this.hotel_btod;}
        else if(var.equals("hotel_dtob")){return this.hotel_dtob;}
        else if(var.equals("hotel_placeid_br")){return this.hotel_placeid_br;}
        else if(var.equals("hotel_placeid_ctr")){return this.hotel_placeid_ctr;}
        else if(var.equals("hotel_placeid_dtob")){return hotel_placeid_dtob;}
        else if(var.equals("avg_hotel_ctr")){return this.avg_hotel_ctr;}
        else if(var.equals("avg_hotel_br")){return this.avg_hotel_br;}
        else if(var.equals("avg_hotel_btod")){return this.avg_hotel_btod;}
        else if(var.equals("avg_hotel_rr")){return this.avg_hotel_rr;}
        else if(var.equals("abp_price_diff")){return this.abp_price_diff;}
        else if(var.equals("recom_score")){return this.recom_score;}
        else if(var.equals("is_city_search")){return this.is_city_search;}
        else if(var.equals("is_locality_search")){return this.is_locality_search;}
        else if(var.equals("is_nearby_search")){return this.is_nearby_search;}
        else if(var.equals("vicinity")){return this.vicinity;}
        else if(var.equals("hotel_user_ctr")){return this.hotel_user_ctr;}
        else if(var.equals("hotel_user_br")){return this.hotel_user_br;}
        else if(var.equals("hotel_user_btod")){return this.hotel_user_btod;}
        else if(var.equals("user_cat_ctr")){return this.user_cat_ctr;}
        else if(var.equals("user_cat_br")){return this.user_cat_br;}
        else if(var.equals("user_cat_dtob")){return this.user_cat_dtob;}
        else if(var.equals("avg_user_rr")){return this.avg_user_rr;}
        else if(var.equals("user_abp")){return this.user_abp;}
        else if(var.equals("stayLength")){return this.stayLength;}
        else if(var.equals("is_weekend_checkin")){return this.is_weekend_checkin;}
        else if(var.equals("day_of_week_checkin")){return this.is_weekend_checkin;}
        else if(var.equals("is_weekend_searchDate")){return this.is_weekend_searchDate;}
        else if(var.equals("day_of_week_searchDate")){return day_of_week_searchDate;}
        else if(var.equals("advPurchaseWindow")){return this.advPurchaseWindow;}
        else return 0.0;
    }

    @Override
    public String toString() {
        return "HotelFeatures{" +
                "hotelId='" + hotelId + '\'' +
                ", fprice=" + fprice +
                ", distance=" + distance +
                ", hotelType=" + hotelType +
                ", place_popularity_score=" + place_popularity_score +
                ", ratingCount=" + ratingCount +
                ", ratingMean=" + ratingMean +
                ", discount_per=" + discount_per +
                ", hotel_br=" + hotel_br +
                ", hotel_ctr=" + hotel_ctr +
                ", hotel_dtob=" + hotel_dtob +
                ", hotel_btod=" + hotel_btod +
                ", hotel_placeid_ctr=" + hotel_placeid_ctr +
                ", hotel_placeid_br=" + hotel_placeid_br +
                ", hotel_placeid_dtob=" + hotel_placeid_dtob +
                ", avg_hotel_rr=" + avg_hotel_rr +
                ", avg_hotel_br=" + avg_hotel_br +
                ", avg_hotel_ctr=" + avg_hotel_ctr +
                ", avg_hotel_btod=" + avg_hotel_btod +
                ", abp_price_diff=" + abp_price_diff +
                ", recom_score=" + recom_score +
                ", is_nearby_search=" + is_nearby_search +
                ", is_locality_search=" + is_locality_search +
                ", is_city_search=" + is_city_search +
                ", vicinity=" + vicinity +
                ", hotel_user_br=" + hotel_user_br +
                ", hotel_user_ctr=" + hotel_user_ctr +
                ", hotel_user_btod=" + hotel_user_btod +
                ", user_cat_ctr=" + user_cat_ctr +
                ", user_cat_br=" + user_cat_br +
                ", user_cat_dtob=" + user_cat_dtob +
                ", avg_user_rr=" + avg_user_rr +
                ", user_abp=" + user_abp +
                ", stayLength=" + stayLength +
                ", is_weekend_checkin=" + is_weekend_checkin +
                ", day_of_week_checkin=" + day_of_week_checkin +
                ", is_weekend_searchDate=" + is_weekend_searchDate +
                ", day_of_week_searchDate=" + day_of_week_searchDate +
                ", advPurchaseWindow=" + advPurchaseWindow +
                '}';
    }

    public HashMap<String, List<Double>> getHotelDetails(RankingRequest request) {
        String experimentId = request.getExperimentId();

        // selected features list based on experimentId
        List<String> selectedFeatures = ExperimentDecider.getSelectedFeatures(experimentId);

        // request dataset for flask application
        HashMap<String, List<Double>> hotelDetails = new HashMap<>();

        //fetch all data from redis
        Iterable<HotelData> result = redisHotelService.mgetHotels(request.getHotels());
        Iterator<HotelData> iter = result.iterator();
        while (iter.hasNext()) {

            //fetch hotel properties from redis
            HotelData hotelData = iter.next();

            //construct datatset for given hotel
            FeaturesAggregator featuresAggregator = new FeaturesAggregator(hotelData.getHotelId(), hotelData.getFprice(), hotelData.getDistance(), hotelData.getHotelType(), hotelData.getPlace_popularity_score(), hotelData.getRatingCount(), hotelData.getRatingMean(), hotelData.getDiscount_per(), hotelData.getHotel_br(), hotelData.getHotel_ctr(), hotelData.getHotel_dtob(), hotelData.getHotel_btod(), hotelData.getHotel_placeid_ctr(),
                    hotelData.getHotel_placeid_br(), hotelData.getHotel_placeid_dtob(), hotelData.getAvg_hotel_rr(), hotelData.getAvg_hotel_br(), hotelData.getAvg_hotel_ctr(), hotelData.getAvg_hotel_btod(), hotelData.getAbp_price_diff(), hotelData.getRecom_score(),
                    (double) request.getIs_nearby_search(), (double) request.getIs_locality_search(), (double) request.getIs_city_search(), hotelData.getVicinity(),
                    userData.getHotel_user_br(), userData.getHotel_user_ctr(), userData.getHotel_user_btod(), userData.getUser_cat_br(), userData.getUser_cat_ctr(), userData.getUser_cat_dtob(), userData.getAvg_user_rr(), userData.getUser_abp(), userData.getStayLength(), userData.getIs_weekend_checkin(), userData.getDay_of_week_checkin(), userData.getIs_weekend_searchDate(), userData.getDay_of_week_searchDate(), userData.getAdvPurchaseWindow()
            );

            // add selected features value to list
            List<Double> selectedFeaturesValues = new ArrayList<>();
            for (int j = 0; j < selectedFeatures.size(); j++) {
                selectedFeaturesValues.add(featuresAggregator.getValue(selectedFeatures.get(j)));
            }
            hotelDetails.put(hotelData.getHotelId(), selectedFeaturesValues);
        }

        //------------------------------collect data from elastic search ---------------------------------------------------------------

        if (hotelDetails.size() != request.getHotels().size()) {
            //list of hotels not present in redis
            List<String> remainingHotels = new ArrayList<>();
            for (int i = 0; i < request.getHotels().size(); i++) {
                if (!hotelDetails.containsKey(request.getHotels().get(i))) {
                    remainingHotels.add(request.getHotels().get(i));
                }
            }

            //System.out.println("remaining Hotels");
            //System.out.println(remainingHotels);

            //fetch remaining hotels from elastic search
            List<HotelData> hotelResponse = elasticHotelService.mgetHotelsEs(remainingHotels);

            //System.out.println(hotelResponse);

            for (int i = 0; i < hotelResponse.size(); i++) {
                //fetch hotel properties from elastic search response
                HotelData hotelData = hotelResponse.get(i);

                //construct datatset for given hotel
                FeaturesAggregator featuresAggregator = new FeaturesAggregator(hotelData.getHotelId(), hotelData.getFprice(), hotelData.getDistance(), hotelData.getHotelType(), hotelData.getPlace_popularity_score(), hotelData.getRatingCount(), hotelData.getRatingMean(), hotelData.getDiscount_per(), hotelData.getHotel_br(), hotelData.getHotel_ctr(), hotelData.getHotel_dtob(), hotelData.getHotel_btod(), hotelData.getHotel_placeid_ctr(),
                        hotelData.getHotel_placeid_br(), hotelData.getHotel_placeid_dtob(), hotelData.getAvg_hotel_rr(), hotelData.getAvg_hotel_br(), hotelData.getAvg_hotel_ctr(), hotelData.getAvg_hotel_btod(), hotelData.getAbp_price_diff(), hotelData.getRecom_score(),
                        (double) request.getIs_nearby_search(), (double) request.getIs_locality_search(), (double) request.getIs_city_search(), hotelData.getVicinity(),
                        userData.getHotel_user_br(), userData.getHotel_user_ctr(), userData.getHotel_user_btod(), userData.getUser_cat_br(), userData.getUser_cat_ctr(), userData.getUser_cat_dtob(), userData.getAvg_user_rr(), userData.getUser_abp(), userData.getStayLength(), userData.getIs_weekend_checkin(), userData.getDay_of_week_checkin(), userData.getIs_weekend_searchDate(), userData.getDay_of_week_searchDate(), userData.getAdvPurchaseWindow()
                );

                // add selected features value to list
                List<Double> selectedFeaturesValues = new ArrayList<>();
                for (int j = 0; j < selectedFeatures.size(); j++) {
                    selectedFeaturesValues.add(featuresAggregator.getValue(selectedFeatures.get(j)));
                }
                hotelDetails.put(hotelData.getHotelId(), selectedFeaturesValues);
            }
        }
        return hotelDetails;
    }
}

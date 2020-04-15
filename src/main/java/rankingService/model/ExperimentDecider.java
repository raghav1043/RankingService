package rankingService.model;

import java.util.*;

public class ExperimentDecider {

    static Map<String,String> experimentToModelName = new HashMap<>();
    static Map<String, List<String>> modelToFeatures = new HashMap<String,List<String>>();

    static {
        experimentToModelName.put("1","model1");
    }

    static {
        List<String> model1List = Arrays.asList("fprice", "hotelType", "user_abp", "discount_per", "hotel_btod",
                "hotel_user_br", "hotel_user_ctr", "hotel_user_btod", "avg_hotel_rr", "is_weekend_checkin",
                "recom_score", "ratingMean", "ratingCount", "is_city_search", "hotel_placeid_ctr", "hotel_placeid_br",
                "hotel_placeid_dtob","user_cat_ctr","user_cat_br","user_cat_dtob", "distance");
        modelToFeatures.put("model1",model1List);
    }

    public static List<String> getSelectedFeatures(String experimentId) {
        String modelName = getSelectedModel(experimentId);
        List<String> selectedFeatures = new ArrayList<String>();
        selectedFeatures = modelToFeatures.get(modelName);
        return selectedFeatures;
    }

    public static String getSelectedModel(String experimentId){
        return experimentToModelName.get(experimentId);
    }

}

package rankingService.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rankingService.entities.RankingRequest;

import java.util.HashMap;
import java.util.List;

@Component
public class RankingModel {

    @Autowired
    FeaturesAggregator featuresAggregator;

    @Autowired
    ScorePredictor scorePredictor;


    public List<String> getSortedHotels(RankingRequest request) {
        HashMap<String,List<Double>> hotelDetails = featuresAggregator.getHotelDetails(request);
        String model = ExperimentDecider.getSelectedModel(request.getExperimentId());
        List<String> sortedHotels = scorePredictor.getScore2(model,hotelDetails);

        return sortedHotels;
    }

}

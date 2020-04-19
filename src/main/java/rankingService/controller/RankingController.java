package rankingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rankingService.model.RankingModel;
import rankingService.entities.RankingRequest;

import java.util.List;

@RestController
public class RankingController {

    @Autowired
    RankingModel rankingModel;

    @RequestMapping(
            value = "/ranking",
            method = RequestMethod.POST)
    @ResponseBody
    public List<String> returnResponse(@RequestBody RankingRequest request){
        List<String> selectedFeatures = rankingModel.getSortedHotels(request);
        return selectedFeatures;
    }

}

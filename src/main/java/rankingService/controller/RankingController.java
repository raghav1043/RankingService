package rankingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rankingService.model.RankingHelper;
import rankingService.request.RankingRequest;

import java.util.List;

@RestController
public class RankingController {

    @Autowired
    RankingHelper rankingHelper ;

    @RequestMapping(
            value = "/ranking",
            method = RequestMethod.POST)
    @ResponseBody
    public List<String> returnResponse(@RequestBody RankingRequest request){
        List<String> selectedFeatures = rankingHelper.getSortedHotels(request);
        return selectedFeatures;
    }

}

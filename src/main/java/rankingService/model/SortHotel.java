package rankingService.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SortHotel {

    public List<String> sortHotelsOnScore(Map<String,Double> map) {

        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(map.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String,Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        //sorted hotel list
        List<String> sortedHotels = new ArrayList<>();
        for(Map.Entry<String,Double> m : list){
            sortedHotels.add(m.getKey());
        }
        return sortedHotels;
    }
}

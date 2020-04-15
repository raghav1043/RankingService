package rankingService.model;

import org.springframework.stereotype.Component;

@Component
public class UserData {
    public Double hotel_user_br  = 0.0;
    public Double hotel_user_ctr  = 1.5;
    public Double hotel_user_btod = 0.0;

    public Double user_cat_ctr = 0.106;
    public Double user_cat_br = 0.0;
    public Double user_cat_dtob  = 0.0;

    public Double avg_user_rr  = 100.0;
    public Double user_abp  = 1200.0;
    public Double stayLength  = 4.0;
    public Double is_weekend_checkin = 1.0;
    public Double day_of_week_checkin = 1.0;
    public Double is_weekend_searchDate = 0.0;
    public Double day_of_week_searchDate = 4.0;
    public Double advPurchaseWindow  = 0.0;

    public UserData(){
    }

}

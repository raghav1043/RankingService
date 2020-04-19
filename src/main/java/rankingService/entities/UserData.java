package rankingService.entities;

import org.springframework.stereotype.Component;

@Component
public class UserData {
    private Double hotel_user_br  = 0.0;
    private Double hotel_user_ctr  = 1.5;
    private Double hotel_user_btod = 0.0;

    private Double user_cat_ctr = 0.106;
    private Double user_cat_br = 0.0;
    private Double user_cat_dtob  = 0.0;

    private Double avg_user_rr  = 100.0;
    private Double user_abp  = 1200.0;
    private Double stayLength  = 4.0;
    private Double is_weekend_checkin = 1.0;
    private Double day_of_week_checkin = 1.0;
    private Double is_weekend_searchDate = 0.0;
    private Double day_of_week_searchDate = 4.0;
    private Double advPurchaseWindow  = 0.0;

    public Double getHotel_user_br() {
        return hotel_user_br;
    }

    public Double getHotel_user_ctr() {
        return hotel_user_ctr;
    }

    public Double getHotel_user_btod() {
        return hotel_user_btod;
    }

    public Double getUser_cat_ctr() {
        return user_cat_ctr;
    }

    public Double getUser_cat_br() {
        return user_cat_br;
    }

    public Double getUser_cat_dtob() {
        return user_cat_dtob;
    }

    public Double getAvg_user_rr() {
        return avg_user_rr;
    }

    public Double getUser_abp() {
        return user_abp;
    }

    public Double getStayLength() {
        return stayLength;
    }

    public Double getIs_weekend_checkin() {
        return is_weekend_checkin;
    }

    public Double getDay_of_week_checkin() {
        return day_of_week_checkin;
    }

    public Double getIs_weekend_searchDate() {
        return is_weekend_searchDate;
    }

    public Double getDay_of_week_searchDate() {
        return day_of_week_searchDate;
    }

    public Double getAdvPurchaseWindow() {
        return advPurchaseWindow;
    }
}

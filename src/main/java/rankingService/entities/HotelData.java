package rankingService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Hotel")
public class HotelData implements Serializable {
    @Id
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
    private Double vicinity;
    private Double recom_score;

    public HotelData(String hotelId, Double fprice, Double distance, Double hotelType, Double place_popularity_score, Double ratingCount, Double ratingMean, Double discount_per, Double hotel_br, Double hotel_ctr, Double hotel_dtob, Double hotel_btod, Double hotel_placeid_ctr, Double hotel_placeid_br, Double hotel_placeid_dtob, Double avg_hotel_rr, Double avg_hotel_br, Double avg_hotel_ctr, Double avg_hotel_btod, Double abp_price_diff, Double vicinity, Double recom_score) {
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
        this.vicinity = vicinity;
        this.recom_score = recom_score;
    }

    public String getHotelId() {
        return hotelId;
    }

    public Double getFprice() {
        return fprice;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getHotelType() {
        return hotelType;
    }

    public Double getPlace_popularity_score() {
        return place_popularity_score;
    }

    public Double getRatingCount() {
        return ratingCount;
    }

    public Double getRatingMean() {
        return ratingMean;
    }

    public Double getDiscount_per() {
        return discount_per;
    }

    public Double getHotel_br() {
        return hotel_br;
    }

    public Double getHotel_ctr() {
        return hotel_ctr;
    }

    public Double getHotel_dtob() {
        return hotel_dtob;
    }

    public Double getHotel_btod() {
        return hotel_btod;
    }

    public Double getHotel_placeid_ctr() {
        return hotel_placeid_ctr;
    }

    public Double getHotel_placeid_br() {
        return hotel_placeid_br;
    }

    public Double getHotel_placeid_dtob() {
        return hotel_placeid_dtob;
    }

    public Double getAvg_hotel_rr() {
        return avg_hotel_rr;
    }

    public Double getAvg_hotel_br() {
        return avg_hotel_br;
    }

    public Double getAvg_hotel_ctr() {
        return avg_hotel_ctr;
    }

    public Double getAvg_hotel_btod() {
        return avg_hotel_btod;
    }

    public Double getAbp_price_diff() {
        return abp_price_diff;
    }

    public Double getVicinity() {
        return vicinity;
    }

    public Double getRecom_score() {
        return recom_score;
    }

    @Override
    public String toString() {
        return "HotelData{" +
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
                ", vicinity=" + vicinity +
                ", recom_score=" + recom_score +
                '}';
    }
}

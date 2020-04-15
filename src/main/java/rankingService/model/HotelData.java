package rankingService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Hotel")
public class HotelData implements Serializable {
    @Id
    public String hotelId;
    public float fprice;
    public float distance;
    public int hotelType;
    public float place_popularity_score;
    public int ratingCount;
    public float ratingMean;
    public float discount_per;
    public float hotel_br;
    public float hotel_ctr;
    public float hotel_dtob;
    public float hotel_btod;
    public float hotel_placeid_ctr;
    public float hotel_placeid_br;
    public float hotel_placeid_dtob;
    public float avg_hotel_rr;
    public float avg_hotel_br;
    public float avg_hotel_ctr;
    public float avg_hotel_btod;
    public float abp_price_diff;
    public int vicinity;
    public float recom_score;

    public String getHotelId() {
        return hotelId;
    }

    @Override
    public String toString() {
        return "ElasticHotelData{" +
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

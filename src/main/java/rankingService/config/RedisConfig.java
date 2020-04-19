package rankingService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import rankingService.entities.HotelData;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, HotelData> redisTemplate(){
        final RedisTemplate<String , HotelData> redisTemplate = new RedisTemplate<String,HotelData>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer( new GenericToStringSerializer<HotelData>(HotelData.class));
        return redisTemplate;
    }

}


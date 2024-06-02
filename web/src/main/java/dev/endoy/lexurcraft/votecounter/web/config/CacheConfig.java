package dev.endoy.lexurcraft.votecounter.web.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig
{

    @Bean
    public Caffeine caffeine( @Value( "${cache.expiry.minutes}" ) int expiryMinutes )
    {
        return Caffeine.newBuilder()
            .expireAfterWrite( expiryMinutes, TimeUnit.MINUTES )
            .recordStats();
    }

    @Bean
    public CacheManager cacheManager( Caffeine caffeine )
    {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine( caffeine );
        return caffeineCacheManager;
    }
}

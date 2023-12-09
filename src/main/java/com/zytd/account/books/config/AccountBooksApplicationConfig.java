package com.zytd.account.books.config;

import com.zytd.account.books.common.utils.CacheUtil;
import com.zytd.account.books.common.utils.LocalCacheUtil;
import com.zytd.account.books.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class AccountBooksApplicationConfig {


    @Configuration
    @Order(1)
    @ConditionalOnProperty(prefix = "cache",name = "enable",havingValue ="redis")
    public static class RedisConfig {

        @Value("${redis.ip}")
        public String ip;
        @Value("${redis.port}")
        public int port;
        @Value("${redis.password}")
        public String password;

        @Bean
        public CacheUtil redisUtil(){
            return new RedisUtil(ip, port, password);
        }
    }

    @Configuration
    @ConditionalOnMissingBean(name = "cacheUtil")
    public static class LocalCacheConfig {
        @Bean
        public CacheUtil localCacheUtil(){
            return new LocalCacheUtil();
        }
    }

}

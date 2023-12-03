package com.zytd.account.books.config;

import com.zytd.account.books.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBooksApplicationConfig {

    @Value("${redis.ip}")
    public String ip;
    @Value("${redis.port}")
    public int port;
    @Value("${redis.password}")
    public String password;

    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil(ip, port, password);
    }
}

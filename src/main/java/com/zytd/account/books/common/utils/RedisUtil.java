package com.zytd.account.books.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

//@Component
public class RedisUtil extends AbstractCacheUtil {

    public RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    public RedisUtil(String ip, int port, String password) {
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(ip, port);
        standaloneConfig.setPassword(password);

        JedisConnectionFactory factory = new JedisConnectionFactory(standaloneConfig);
        factory.afterPropertiesSet();

        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String getStringValue(String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        return Objects.nonNull(obj) ? String.valueOf(obj) : "";
    }

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setValue(String key, Object value, long second) {
        redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }

    @Override
    public boolean setIfAbsent(String key, Object value) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value));
    }

    @Override
    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }

}

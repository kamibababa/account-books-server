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
public class RedisUtil {
    @Value("${redis.ip}")
    public String ip;
    @Value("${redis.port}")
    public int port;
    @Value("${redis.password}")
    public String password;

    public RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    public RedisUtil(String ip, int port, String password){
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(ip, port);
        standaloneConfig.setPassword(password);

        JedisConnectionFactory factory =new JedisConnectionFactory(standaloneConfig);
        factory.afterPropertiesSet();

        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
    }

    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public String getStringValue(String key){
        Object obj = redisTemplate.opsForValue().get(key);
        return Objects.nonNull(obj) ? String.valueOf(obj) : "";
    }

    public void setValue(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    public void setValue(String key, Object value, long second){
        redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }

    public boolean setIfAbsent(String key, Object value){
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, value));
    }

    public void deleteValue(String key){
        redisTemplate.delete(key);
    }

    /**
     * 根据会员id、请求地址来判断是否重复提交
     */
    public boolean repeatCommit(Long memberId, String requestMapping, long second){
        final String flag = "repeat";
        if (Objects.isNull(memberId) || StringUtils.isEmpty(requestMapping) || second <= 0) {
            throw new IllegalArgumentException("memberId或requestMapping标识不能为空,超时时间(秒)不能小于或等于0");
        }
        final String key = memberId + "_" + requestMapping;

        String value = getStringValue(key);
        if(!StringUtils.isEmpty(value))
            return false;

        setValue(key, flag, second);
        return true;
    }
}

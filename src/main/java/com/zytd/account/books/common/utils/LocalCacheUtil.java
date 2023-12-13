package com.zytd.account.books.common.utils;


import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地内存
 */
public class LocalCacheUtil extends AbstractCacheUtil {

    private ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();
    /**
     * 过期时间支持最小到秒
     */
    private ConcurrentHashMap<String, Date> expireCache = new ConcurrentHashMap<>();

    @Override
    public Object getValue(String key) {
        return cache.get(key);
    }

    @Override
    public String getStringValue(String key) {
        Object obj = cache.get(key);
        // 是否过期
        Date date = expireCache.get(key);
        if (date != null && new Date().after(date)) {
            deleteValue(key);
            return "";
        }
        return Objects.nonNull(obj) ? String.valueOf(obj) : "";
    }

    @Override
    public void setValue(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void setValue(String key, Object value, long second) {
        cache.put(key, value);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, Long.valueOf(second).intValue());
        expireCache.put(key, instance.getTime());
    }

    @Override
    public boolean setIfAbsent(String key, Object value) {
        Object o = cache.get(key);
        if (Objects.nonNull(o)) {
            return Boolean.FALSE;
        }
        cache.put(key, value);
        return Boolean.TRUE;
    }

    @Override
    public void deleteValue(String key) {
        cache.remove(key);
        expireCache.remove(key);
    }

    /**
     * TODO 定时任务定时清除过期的key，防止内存泄漏
     */

}

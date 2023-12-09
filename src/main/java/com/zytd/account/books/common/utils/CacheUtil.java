package com.zytd.account.books.common.utils;

public interface CacheUtil {

    Object getValue(String key);

    String getStringValue(String key);

    void setValue(String key, Object value);

    void setValue(String key, Object value, long second);

    boolean setIfAbsent(String key, Object value);

    void deleteValue(String key);
}

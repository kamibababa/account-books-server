package com.zytd.account.books.common.utils;

import org.springframework.beans.factory.config.BeanPostProcessor;

public interface CacheUtil extends BeanPostProcessor {

    Object getValue(String key);

    String getStringValue(String key);

    void setValue(String key, Object value);

    void setValue(String key, Object value, long second);

    boolean setIfAbsent(String key, Object value);

    void deleteValue(String key);
}

package com.zytd.account.books.common.utils;

import org.springframework.util.StringUtils;

import java.util.Objects;

public abstract class AbstractCacheUtil implements CacheUtil{

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

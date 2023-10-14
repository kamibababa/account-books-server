package com.zytd.account.books.common.utils;

import com.zytd.account.books.common.base.BizException;

public class AssertUtils {

    /**
     * 如果 b为true则通过，否则抛异常
     * @param b
     * @param errorMsg
     */
    public static void assertTrue(boolean b, String errorMsg){
        if(!b){
            throw new BizException(errorMsg);
        }
    }
}

package com.zytd.account.books.common.base;

import java.util.HashMap;
import java.util.Map;

public class ExceptionCodeConstants {
    public static Map<Integer, String> errorCodeMap = new HashMap<>();
    public static final Integer PARAM_ERROR = 10001;

    static {
        errorCodeMap.put(10001, "参数验证错误");
    }

    public String getMessage(Integer errorCode){
        return errorCodeMap.get(errorCode);
    }
}

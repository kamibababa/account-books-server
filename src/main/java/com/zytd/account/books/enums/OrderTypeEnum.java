package com.zytd.account.books.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderTypeEnum {
    //1-批发 2-零售
    wholesale(1, "批发"),
    retail(2, "零售"),
    ;

    private Integer code;
    private String message;

    public static String getMessage(Integer code){
        for (OrderTypeEnum orderTypeEnum : OrderTypeEnum.values()) {
            if(orderTypeEnum.getCode().equals(code))
                return orderTypeEnum.getMessage();
        }
        return null;
    }
}

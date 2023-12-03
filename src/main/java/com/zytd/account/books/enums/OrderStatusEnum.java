package com.zytd.account.books.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    //1-已结清 2-未结清
    closed_account(1, "已结清"),
    uncleared(2, "未结清"),
    ;

    private Integer code;
    private String message;
}

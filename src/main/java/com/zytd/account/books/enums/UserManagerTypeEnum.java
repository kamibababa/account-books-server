package com.zytd.account.books.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserManagerTypeEnum {
    //1-客户 2-供货人
    customer(1, "客户"),
    supplier(2, "供货人"),
    ;

    private Integer code;
    private String message;
}

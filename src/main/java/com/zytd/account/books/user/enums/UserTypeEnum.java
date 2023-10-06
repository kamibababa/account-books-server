package com.zytd.account.books.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserTypeEnum {

    ADMIN(0, "超级管理员"),
    C_USER(1, "C端用户"),

    ;

    private Integer code;
    private String desc;
}

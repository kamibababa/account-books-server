package com.zytd.account.books.common.base;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    AUTH_FAIL(-2,"认证失败"),
    LOGIN_FAIL(-1,"登录失败"),
    LOGIN_SUCCESS(0,"登录成功"),
    ERROR(500,"系统错误"),
    SUCCESS(200,"调用成功")
    ;

    private Integer code;
    private String desc;
}

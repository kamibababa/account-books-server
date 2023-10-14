package com.zytd.account.books.common.base;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    ERROR(500,"系统错误"),
    SUCCESS(200,"调用成功")
    ;

    private Integer code;
    private String desc;
}

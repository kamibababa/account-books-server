package com.zytd.account.books.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IsDelEnum {

    NO(0,"正常"),
    YES(1,"删除"),
    ;

    private Integer code;
    private String desc;

    public static IsDelEnum getByCode(Integer code) {
        for (IsDelEnum value : values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}

package com.zytd.account.books.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum YesOrNoEnum {

    NO(2,"否"),
    YES(1,"是"),
    ;

    private Integer code;
    private String desc;

    public static YesOrNoEnum getByCode(Integer code) {
        for (YesOrNoEnum value : values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}

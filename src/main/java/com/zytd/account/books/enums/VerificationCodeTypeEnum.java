package com.zytd.account.books.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VerificationCodeTypeEnum {

    login(1,"注册/登录"),
    DISABLE(2,""),
    ;

    private Integer code;
    private String desc;

    public static VerificationCodeTypeEnum getByCode(Integer code) {
        for (VerificationCodeTypeEnum value : values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}

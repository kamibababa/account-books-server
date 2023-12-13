package com.zytd.account.books.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VerificationCodeTypeEnum {

    SMS(1,"sms","短信"),
    IMAGE(2,"image","图片验证码"),
    ;

    private Integer code;
    private String type;
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

package com.zytd.account.books.user.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatusEnum {

    ENABLE(1,"启用"),
    DISABLE(2,"禁用"),
    ;

    private Integer code;
    private String desc;

    public static UserStatusEnum getByCode(Integer code) {
        for (UserStatusEnum value : values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}

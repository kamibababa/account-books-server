package com.zytd.account.books.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountBookStatusEnum {

    UN_PAY(1,"未结清"),
    PART_PAY(2,"部分结清"),
    PAY(3,"已结清"),
    ;

    private Integer code;
    private String desc;

    public static AccountBookStatusEnum getByCode(Integer code) {
        for (AccountBookStatusEnum value : values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}

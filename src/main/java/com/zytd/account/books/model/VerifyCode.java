package com.zytd.account.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyCode {

    private String username;

    private String verifyCode;

    /**
     * 1 sms 2 image
     */
    private String type;

    /**
     * 过期时间：秒
     */
    private Integer expire;

}

package com.zytd.account.books.biz.accountuser.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    private Integer userId;

    private String username;

    private String mobile;

    private String address;

    private String remark;

    private String  status;

    private String createDate;

    private String endDate;

}

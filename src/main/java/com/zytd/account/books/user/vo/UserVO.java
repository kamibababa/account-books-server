package com.zytd.account.books.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

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

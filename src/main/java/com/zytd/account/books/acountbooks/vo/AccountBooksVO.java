package com.zytd.account.books.acountbooks.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountBooksVO implements Serializable {

    private Integer userId;

    private String username;

    private String mobile;

    private String address;

    private String remark;

    private String  status;

    private BigDecimal accountAmount;

    private String createDate;

    private String endDate;

}

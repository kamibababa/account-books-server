package com.zytd.account.books.biz.accountbooks.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountBooksVO implements Serializable {

    private Integer id;

    private Integer userId;

    private String username;

    private String mobile;

    private String area;

    private String areaDetail;

    private String remark;

    private String  status;

    private BigDecimal accountAmount;

    private String createDate;

    private String endDate;

}

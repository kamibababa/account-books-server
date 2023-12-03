package com.zytd.account.books.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountUserVO implements Serializable {

    private Integer userId;

    private String username;

    private String mobile;

    private String area;

    private String areaDetail;

    private String remark;

    private String  status;

    private String createDate;

}

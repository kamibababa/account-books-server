package com.zytd.account.books.biz.accountbooks.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountBooksBO  implements Serializable {

    private Integer id;

    private Integer userId;

    private String username;

    private String mobile;

    private String address;

    private String remark;

    private String status;

}

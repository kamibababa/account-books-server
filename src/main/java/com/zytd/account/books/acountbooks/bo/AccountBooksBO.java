package com.zytd.account.books.acountbooks.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountBooksBO  implements Serializable {

    private Integer userId;

    private String username;

    private String mobile;

    private String address;

    private String remark;

    private Integer status;

}

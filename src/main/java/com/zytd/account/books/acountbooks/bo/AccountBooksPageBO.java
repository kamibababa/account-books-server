package com.zytd.account.books.acountbooks.bo;

import com.zytd.account.books.common.base.AbstractPageBO;
import lombok.Data;

@Data
public class AccountBooksPageBO extends AbstractPageBO {

    private Integer id;

    private String username;

    private String mobile;

    private String remark;


}

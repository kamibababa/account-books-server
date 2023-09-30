package com.zytd.account.books.acountbooks.bo;

import com.zytd.account.books.config.base.AbstractPageBO;
import lombok.Data;

@Data
public class AccountBooksPageBO extends AbstractPageBO {

    private String username;

    private String mobile;

    private String remark;


}

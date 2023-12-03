package com.zytd.account.books.bo.user;

import com.zytd.account.books.common.base.AbstractPageBO;
import lombok.Data;

@Data
public class AccountUserPageBO extends AbstractPageBO {

    private String username;

    private String mobile;

    private String remark;

    private Integer userType;


}

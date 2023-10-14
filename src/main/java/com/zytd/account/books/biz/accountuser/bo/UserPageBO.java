package com.zytd.account.books.biz.accountuser.bo;

import com.zytd.account.books.common.base.AbstractPageBO;
import lombok.Data;

@Data
public class UserPageBO extends AbstractPageBO {

    private String username;

    private String mobile;

    private String remark;

    private Integer userType;


}

package com.zytd.account.books.user.bo;

import com.zytd.account.books.config.base.AbstractPageBO;
import lombok.Data;

@Data
public class UserPageBO extends AbstractPageBO {

    private String username;

    private String mobile;

    private String remark;

    private Integer userType;


}

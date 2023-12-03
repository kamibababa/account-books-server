package com.zytd.account.books.bo.user;

import com.zytd.account.books.common.base.BaseBO;
import lombok.Data;

@Data
public class AccountUserBo extends BaseBO {

    private Integer userId;

    private String username;

    private String mobile;

    private String area;

    private String areaDetail;

    private String remark;

    private String  status;

}

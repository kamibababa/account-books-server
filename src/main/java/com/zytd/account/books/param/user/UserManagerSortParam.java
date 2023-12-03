package com.zytd.account.books.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserManagerSortParam implements Serializable {
    @ApiModelProperty(value = "排序列表")
    private List<UserManagerSort> sortList;

    @Data
    public static class UserManagerSort{
        @ApiModelProperty(value = "用户管理id")
        private Long userManagerId;

        @ApiModelProperty(value = "排序的序号 从小到大")
        private Integer serialNum;
    }
}

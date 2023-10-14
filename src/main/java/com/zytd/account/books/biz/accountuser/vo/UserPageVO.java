package com.zytd.account.books.biz.accountuser.vo;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.util.List;

@Data
public class UserPageVO extends Page<UserVO> {

    public UserPageVO(int PageNum, int pageSize, int total, List<UserVO> data){
        super(PageNum,pageSize);
        setRecords(data);
        setTotal(total);
    }
}

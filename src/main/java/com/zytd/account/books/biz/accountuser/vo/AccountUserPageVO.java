package com.zytd.account.books.biz.accountuser.vo;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.util.List;

@Data
public class AccountUserPageVO extends Page<AccountUserVO> {

    public AccountUserPageVO(int PageNum, int pageSize, int total, List<AccountUserVO> data){
        super(PageNum,pageSize);
        setRecords(data);
        setTotal(total);
    }
}

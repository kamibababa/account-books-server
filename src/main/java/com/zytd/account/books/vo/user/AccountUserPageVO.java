package com.zytd.account.books.vo.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

package com.zytd.account.books.acountbooks.vo;

import com.baomidou.mybatisplus.plugins.Page;
import com.zytd.account.books.acountbooks.bo.AccountBooksPageBO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountBooksPageVO extends Page<AccountBooksVO> {

    private BigDecimal totalAmount;

    public AccountBooksPageVO(int PageNum, int pageSize, int total, List<AccountBooksVO> data){
        super(PageNum,pageSize);
        setRecords(data);
        setTotal(total);
    }
}

package com.zytd.account.books.vo.book;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

package com.zytd.account.books.vo.income;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class IncomeOrderPagePageVO extends Page<IncomeOrderPageVO> {

    private Integer totalMoney;

    public IncomeOrderPagePageVO(long PageNum, long pageSize, long total, List<IncomeOrderPageVO> data){
        super(PageNum,pageSize);
        setRecords(data);
        setTotal(total);
    }

}

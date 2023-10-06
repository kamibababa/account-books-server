package com.zytd.account.books.user.vo;

import com.baomidou.mybatisplus.plugins.Page;
import com.zytd.account.books.acountbooks.vo.AccountBooksVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserPageVO extends Page<UserVO> {

    public UserPageVO(int PageNum, int pageSize, int total, List<UserVO> data){
        super(PageNum,pageSize);
        setRecords(data);
        setTotal(total);
    }
}

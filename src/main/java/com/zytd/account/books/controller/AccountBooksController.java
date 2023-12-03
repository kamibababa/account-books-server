package com.zytd.account.books.controller;

import com.zytd.account.books.bo.book.AccountBooksBO;
import com.zytd.account.books.bo.book.AccountBooksPageBO;
import com.zytd.account.books.service.AccountBooksService;
import com.zytd.account.books.vo.book.AccountBooksPageVO;
import com.zytd.account.books.vo.book.AccountBooksVO;
import com.zytd.account.books.common.base.BaseController;
import com.zytd.account.books.common.base.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/accountbook")
public class AccountBooksController extends BaseController {

    @Autowired
    private AccountBooksService accountBooksService;

    @RequestMapping("/list")
    public AccountBooksPageVO list(@RequestBody AccountBooksPageBO bo){
        return accountBooksService.list(bo);
    }

    @RequestMapping("/add")
    public ResultVO add(@RequestBody AccountBooksBO bo){
        log.info(bo.toString());
        return ResultVO.success();
    }

    @RequestMapping("/edit")
    public ResultVO edit(@RequestBody AccountBooksBO bo){
        Assert.notNull(bo.getId(),"不能为空");
        log.info(bo.toString());
        return ResultVO.success();
    }

    @RequestMapping("/delete")
    public ResultVO delete(Integer id){
        Assert.notNull(id,"不能为空");
        log.info(id.toString());
        return ResultVO.error("账本不存在");
    }

    @RequestMapping("/detail")
    public AccountBooksVO detail(Integer id){
        AccountBooksPageBO accountBooksPageBO = new AccountBooksPageBO();
        accountBooksPageBO.setPageNum(1);
        accountBooksPageBO.setPageSize(1000);
        AccountBooksPageVO list = accountBooksService.list(accountBooksPageBO);
        List<AccountBooksVO> booksVOS = list.getRecords().stream().filter(o -> o.getId().equals(id))
                .collect(Collectors.toList());
        if(booksVOS.isEmpty()){
            return null;
        }
        return booksVOS.get(0);
    }

}

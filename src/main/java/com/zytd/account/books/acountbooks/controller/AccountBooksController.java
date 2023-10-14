package com.zytd.account.books.acountbooks.controller;

import com.zytd.account.books.acountbooks.bo.AccountBooksBO;
import com.zytd.account.books.acountbooks.bo.AccountBooksPageBO;
import com.zytd.account.books.acountbooks.service.AccountBooksService;
import com.zytd.account.books.acountbooks.vo.AccountBooksPageVO;
import com.zytd.account.books.acountbooks.vo.AccountBooksVO;
import com.zytd.account.books.config.base.BaseController;
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
    public void add(@RequestBody AccountBooksBO bo){
        log.info(bo.toString());
    }

    @RequestMapping("/edit")
    public void edit(@RequestBody AccountBooksBO bo){
        Assert.notNull(bo.getId(),"不能为空");
        log.info(bo.toString());
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody AccountBooksBO bo){
        Assert.notNull(bo.getId(),"不能为空");
        log.info(bo.toString());
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

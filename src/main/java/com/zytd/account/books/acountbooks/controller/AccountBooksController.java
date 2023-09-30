package com.zytd.account.books.acountbooks.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zytd.account.books.acountbooks.bo.AccountBooksBO;
import com.zytd.account.books.acountbooks.bo.AccountBooksPageBO;
import com.zytd.account.books.acountbooks.enums.AccountBookStatusEnum;
import com.zytd.account.books.acountbooks.service.AccountBooksService;
import com.zytd.account.books.acountbooks.vo.AccountBooksPageVO;
import com.zytd.account.books.acountbooks.vo.AccountBooksVO;
import com.zytd.account.books.config.base.BaseController;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/accountbook")
public class AccountBooksController extends BaseController {

    @Autowired
    private AccountBooksService accountBooksService;

    @RequestMapping("/list")
    public AccountBooksPageVO list(@RequestBody AccountBooksPageBO bo, HttpServletRequest request){
        return accountBooksService.list(bo);
    }
}

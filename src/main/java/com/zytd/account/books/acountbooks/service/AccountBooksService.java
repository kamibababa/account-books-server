package com.zytd.account.books.acountbooks.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zytd.account.books.acountbooks.bo.AccountBooksPageBO;
import com.zytd.account.books.acountbooks.vo.AccountBooksPageVO;
import com.zytd.account.books.acountbooks.vo.AccountBooksVO;

import java.util.List;

public interface AccountBooksService {

    AccountBooksPageVO list(AccountBooksPageBO bo);
}

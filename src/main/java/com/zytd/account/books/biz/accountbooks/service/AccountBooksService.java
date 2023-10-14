package com.zytd.account.books.biz.accountbooks.service;

import com.zytd.account.books.biz.accountbooks.bo.AccountBooksPageBO;
import com.zytd.account.books.biz.accountbooks.vo.AccountBooksPageVO;

public interface AccountBooksService {

    AccountBooksPageVO list(AccountBooksPageBO bo);
}

package com.zytd.account.books.service;

import com.zytd.account.books.bo.book.AccountBooksPageBO;
import com.zytd.account.books.vo.book.AccountBooksPageVO;

public interface AccountBooksService {

    AccountBooksPageVO list(AccountBooksPageBO bo);
}

package com.zytd.account.books.config.base;

import lombok.Data;

@Data
public abstract class AbstractPageBO extends BaseBO{

    protected Integer pageNum;

    protected Integer pageSize;


    public Integer getLimit(){
       return (pageNum - 1) * pageSize;
    }

}

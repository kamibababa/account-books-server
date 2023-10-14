package com.zytd.account.books.common.base;

import lombok.Data;

@Data
public class BizException extends RuntimeException{

    private String msg;
    private Integer code;

    private BizException(){}

    public BizException(String msg){
        super(msg);
        this.code = ResponseCodeEnum.ERROR.getCode();
    }

    public BizException(String msg, Integer code){
        super(msg);
        this.code = code;
    }

}

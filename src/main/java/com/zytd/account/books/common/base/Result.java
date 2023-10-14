package com.zytd.account.books.common.base;


import com.zytd.account.books.common.utils.AssertUtils;
import lombok.Data;

import java.util.Objects;

@Data
public class Result<T> {

    private String msg;
    private T data;
    private Integer code;

    public Result(){}

    public Result(T data){
        this.data = data;
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = ResponseCodeEnum.SUCCESS.getDesc();
    }

    public Result(T data, ResponseCodeEnum response){
        AssertUtils.assertTrue(Objects.nonNull(response),"响应码为空");
        this.data = data;
        this.code = response.getCode();
        this.msg = response.getDesc();
    }

    public Result(String msg, ResponseCodeEnum response){
        AssertUtils.assertTrue(Objects.nonNull(response),"响应码为空");
        this.code = response.getCode();
        this.msg = msg;
    }

    public static <T>  Result<T> success(T data){
        return new Result<>(data);
    }

    public static <T>  Result<T> error(String msg){
        return new Result<>(msg,ResponseCodeEnum.ERROR);
    }

    public static <T>  Result<T> error(String msg, Integer code){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T>  Result<T> success(){
        return new Result<>(null);
    }
}

package com.zytd.account.books.common.base;


import com.zytd.account.books.common.utils.AssertUtils;
import lombok.Data;

import java.util.Objects;

@Data
public class ResultVO<T> {

    private String msg;
    private T data;
    private Integer code;

    public ResultVO(){}

    public ResultVO(T data){
        this.data = data;
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = ResponseCodeEnum.SUCCESS.getDesc();
    }

    public ResultVO(ResponseCodeEnum response,T data){
        AssertUtils.assertTrue(Objects.nonNull(response),"响应码为空");
        this.data = data;
        this.code = response.getCode();
        this.msg = response.getDesc();
    }

    public ResultVO(String msg, ResponseCodeEnum response){
        AssertUtils.assertTrue(Objects.nonNull(response),"响应码为空");
        this.code = response.getCode();
        this.msg = msg;
    }

    public static <T> ResultVO<T> success(T data){
        return new ResultVO<>(data);
    }

    public static <T> ResultVO<T> error(String msg){
        return new ResultVO<>(msg,ResponseCodeEnum.ERROR);
    }

    public static <T> ResultVO<T> error(String msg, Integer code){
        ResultVO<T> result = new ResultVO<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResultVO<T> success(){
        return new ResultVO<>(null);
    }
}

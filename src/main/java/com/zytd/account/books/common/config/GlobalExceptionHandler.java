package com.zytd.account.books.common.config;


import com.zytd.account.books.common.base.BizException;
import com.zytd.account.books.common.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result<String> bizException(BizException e){
        log.error("业务异常",e);
        return Result.error(e.getMsg(),e.getCode());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exception(Exception e){
        log.error("服务异常",e);
        return Result.error(e.getMessage());
    }
}

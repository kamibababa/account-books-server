package com.zytd.account.books.config;


import com.zytd.account.books.common.base.BizException;
import com.zytd.account.books.common.base.ResponseCodeEnum;
import com.zytd.account.books.common.base.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultVO<String> bizException(BizException e){
        log.error("业务异常",e);
        return ResultVO.error(e.getMsg(),e.getCode());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO<String> exception(Exception e){
        log.error("服务异常",e);
        return ResultVO.error(e.getMessage());
    }
}

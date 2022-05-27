package com.zhang.exception;

import com.zhang.base.response.BaseResponse;
import com.zhang.base.response.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandling {

    /**
     * 全局处理异常
     *
     * @param e
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<String> handlePowerException(BusinessException e) {
        log.error(e.getMessage());
        return ResultUtils.error(e.getCode(), e.getMessage());
    }
}

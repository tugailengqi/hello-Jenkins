package com.lengqi.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lengqi.common.result.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 全局系统异常处理
 *
 * @author hxrui
 * @date 2020-02-25 13:54
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVo handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常，异常原因：{}", e.getMessage(), e);
        return ResultVo.failed(e.getMessage());
    }


    @ExceptionHandler(JsonProcessingException.class)
    public ResultVo handleJsonProcessingException(JsonProcessingException e) {
        log.error("Json转换异常，异常原因：{}", e.getMessage(), e);
        return ResultVo.failed(e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public ResultVo handleBizException(BizException e) {
        log.error("业务异常，异常原因：{}", e.getMessage(), e);
        if (e.getResultCode() != null) {
            return ResultVo.failed(e.getResultCode());
        }
        return ResultVo.failed(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVo handleException(Exception e) {
        log.error("未知异常，异常原因：{}", e.getMessage(), e);
        return ResultVo.failed(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResultVo.failed(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler({ArithmeticException.class})
    public ResultVo arithmeticException(ArithmeticException ex) {
        return ResultVo.failed("算术异常"+ex.getMessage());
    }
}

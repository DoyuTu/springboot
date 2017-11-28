package com.doyutu.exception;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice()
public class DefaultException {

    private static final Logger logger = LoggerFactory.getLogger(DefaultException.class);

    @ExceptionHandler(value = {Exception.class})
    public ErrorInfo<String> exceptionHandler(HttpServletRequest req, Exception e) {
        ErrorInfo<String> info = new ErrorInfo<>();
        info.setDate(DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        if (e instanceof MyException) {
            info.setCode(((MyException) e).getCodeEnum().getCode());
            info.setMsg(((MyException) e).getMsg());
            return info;
        } else if (e instanceof RuntimeException) {

            return info;
        } else {
            return null;
        }
    }

}

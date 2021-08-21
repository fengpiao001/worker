package com.apising.worker.config;

import com.apising.common.lang.domain.Result;
import com.apising.common.lang.exception.XException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tianxu
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 自定义异常
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Result handleServiceMessageException(HttpServletRequest request, Throwable e) {
        log.error("ControllerExceptionHandler.handleServiceMessageException ",e);
        if(e instanceof XException){
            XException xe = (XException) e;
            return Result.fail(xe.getErrorCode(), xe.getShowText());
        }else if(e instanceof IllegalArgumentException){
            return Result.fail(501,e.getMessage());
        }
        return Result.fail(500,"系统异常");
    }
}

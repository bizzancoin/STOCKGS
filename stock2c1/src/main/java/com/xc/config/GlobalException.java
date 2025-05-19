package com.xc.config;

import com.xc.common.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ServerResponse exceptionHandler(Exception e){
        //判断异常类型
        e.printStackTrace();
        return ServerResponse.createByErrorMsg("系统异常，请稍后重试");
    }
}

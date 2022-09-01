package com.thecms.config.exception;

import com.thecms.compenont.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class ControllerException {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandler(Exception e){
        log.error(e.getMessage());
        return Result.fail(400,"函数调用错误",null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result runtimeExceptionHandler(RuntimeException runtimeException){
        log.error(runtimeException.getMessage(),new RuntimeException());
        return Result.fail(400,"运行时出错",null);
    }

    /**
     * 未找到User用户
     * @param notFoundUserException
     * @return
     */
    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result notFoundUserException(NotFoundUserException notFoundUserException){
        log.error(notFoundUserException.getMessage());
        return Result.fail(403,notFoundUserException.getMessage(),null);
    }

}

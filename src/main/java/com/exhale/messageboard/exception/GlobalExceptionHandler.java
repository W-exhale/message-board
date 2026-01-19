package com.exhale.messageboard.exception;


import com.exhale.messageboard.common.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    *用户不存在——》json提示
    * 密码错误——》json提示
    * 不再Whitelabel
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handle(RuntimeException e){
        return Result.error(e.getMessage());
    }

    //全局捕获校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValid(MethodArgumentNotValidException e){
        return Result.error(
                e.getBindingResult().getFieldError().getDefaultMessage()
        );
    }

}

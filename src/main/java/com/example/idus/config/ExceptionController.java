package com.example.idus.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice(basePackages = {"com.example.idus"})
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseResult defaultException(Exception e) {
        log.error(e.getMessage());
        return new ResponseResult(e.getMessage());
    }

    @ExceptionHandler(IdusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseResult handleMethodCustom400Exception(IdusException e) {
        log.error(e.getMessage());
        return new ResponseResult(e.getCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e.getBindingResult().getFieldErrors());
        final String defaultMessage = e.getBindingResult().getFieldErrors().stream().findFirst().get().getDefaultMessage();
        return new ResponseResult(Code.VALIDATION_POLICY, defaultMessage);
    }
}
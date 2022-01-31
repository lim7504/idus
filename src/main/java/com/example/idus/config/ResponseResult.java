package com.example.idus.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static com.example.idus.config.Code.SERVER_ERROR;
import static com.example.idus.config.Code.SUCCESS;

@Getter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    private Code code;

    private T data;

    private String message;

    private LocalDateTime timeStamp = LocalDateTime.now();

    @JsonIgnore
    private HttpStatus httpStatus;

    public ResponseResult(Code code) {
        this.code = code;
        this.message = code.getMessage();
    }

    public ResponseResult(String message) {
        this.code = SERVER_ERROR;
        this.message = message;
    }

    public ResponseResult(Code code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseResult<T> ok() {
        return ok(null, null);
    }

    public static <T> ResponseResult<T> ok(T data) {
        return ok(data, null);
    }

    public static <T> ResponseResult<T> ok(T data, String message) {
        return with(data).code(SUCCESS).message(message).httpStatus(HttpStatus.OK);
    }

    private static <T> ResponseResult<T> with(T data) {
        ResponseResult<T> response = new ResponseResult<>();
        response.data = data;
        return response;
    }

    public ResponseResult<T> code(@NotNull Code code) {
        this.code = code;
        return this;
    }

    public ResponseResult<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResponseResult<T> httpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ResponseEntity createResponseEntity() {
        return ResponseEntity.status(this.httpStatus).body(this);
    }

}
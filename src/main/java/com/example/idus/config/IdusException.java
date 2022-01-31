package com.example.idus.config;

import lombok.Getter;

@Getter
public class IdusException extends RuntimeException {

    private Code code;

    public IdusException(Code code) {
        super(code.getMessage());
        this.code = code;
    }
}

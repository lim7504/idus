package com.example.idus.config;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum Code {

    //COMMON
    SUCCESS("CM_0001", "성공하였습니다.", HttpStatus.OK),
    BAD_REQUEST("CM_0002", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    SERVER_ERROR("CM_0003", "서버 에러입니다. 관리자에게 문의하세요.", HttpStatus.INTERNAL_SERVER_ERROR),
    FORBIDDEN("CM_0004", "권한이 없습니다. 관리자에게 문의하세요.", HttpStatus.FORBIDDEN),

    //Validation Policy
    VALIDATION_POLICY("VP_0001", "VALIDATION_POLICY", HttpStatus.BAD_REQUEST),

    //Account
    ACCOUNT_NOT_FOUND("AC_0001", "해당 회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    EMAIL_DUPLICATE("AC_0002", "동일한 회원이메일이 존재합니다.", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EXIST("AC_0003", "존재하지 않는 이메일 입니다.", HttpStatus.BAD_REQUEST);


    private final String code;
    private String message;
    private HttpStatus status;

    Code(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @JsonValue
    public String getCode() {
        return this.code;
    }

}



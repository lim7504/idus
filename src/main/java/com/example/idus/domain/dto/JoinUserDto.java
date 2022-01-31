package com.example.idus.domain.dto;

import com.example.idus.domain.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class JoinUserDto {

    @ApiModelProperty(example = "홍길동")
    @Pattern(regexp = ".*[가-힣a-zA-Z]", message = "이름은 한글과 영문 대소문자만 허용합니다.")
    @Size(max = 20, message = "이름의 최대길이는 20글자 입니다.")
    @NotNull(message = "이름은 필수 값입니다.")
    private String name;

    @ApiModelProperty(example = "hong")
    @Pattern(regexp = ".*[a-z]", message = "별명은 영문 소문자만 허용합니다.")
    @Size(max = 30, message = "별명의 최대길이는 30글자 입니다.")
    @NotNull(message = "별명은 필수 값입니다.")
    private String nickname;

    @ApiModelProperty(example = "!1Abcderfhi")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}", message = "비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함합니다.")
    @Size(min = 10, max = 30, message = "비밀번호 최소길이는 10글자 최대길이는 30글자 입니다.")
    @NotNull(message = "비밀번호는 필수 값입니다.")
    private String password;

    @ApiModelProperty(example = "01000000000")
    @Pattern(regexp = ".*[0-9]", message = "전화번호는 숫자만 가능합니다.")
    @Size(max = 20, message = "전화번호의 최대길이는 20글자 입니다.")
    @NotNull(message = "전화번호는 필수 값입니다.")
    private String mobile;

    @ApiModelProperty(example = "abc@test.com")
    @Pattern(regexp = "[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}", message = "이메일 형식과 맞지 않습니다.")
    @Size(max = 100, message = "이메일의 최대길이는 100글자 입니다.")
    @NotNull(message = "이메일은 필수 값입니다.")
    private String email;

    @ApiModelProperty(example = "MALE")
    private Gender gender;

}

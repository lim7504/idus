package com.example.idus.domain.dto;

import com.example.idus.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OrderDto {

    @ApiModelProperty(example = "1")
    @NotNull(message = "유저ID는 필수 값입니다.")
    private Long userId;

    @ApiModelProperty(example = "맥북")
    @Size(max = 100, message = "상품명에 최대길이는 100글자 입니다.")
    @NotNull(message = "상품명은 필수 값입니다.")
    private String productName;

    @JsonIgnore
    private User user;

}

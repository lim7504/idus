package com.example.idus.domain.dto;

import com.example.idus.domain.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserDto {

    private Long id;

    private String name;

    private String nickname;

    private String password;

    private String mobile;

    private String email;

    private Gender gender;

    private GetOrderDto lastOrder;

}

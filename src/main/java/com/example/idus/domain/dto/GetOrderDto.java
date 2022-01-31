package com.example.idus.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetOrderDto {

    private Long id;

    private String orderKey;

    private String name;

    private String createdDate;

}

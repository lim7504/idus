package com.example.idus.controller;

import com.example.idus.config.ResponseResult;
import com.example.idus.domain.dto.GetOrderDto;
import com.example.idus.domain.dto.OrderDto;
import com.example.idus.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Api(value = "주문")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "상품주문")
    @PostMapping
    public ResponseEntity order(@RequestBody @Validated OrderDto orderDto) {
        this.orderService.order(orderDto);
        return ResponseResult.ok().createResponseEntity();
    }

    @ApiOperation(value = "단일 회원의 주문 목록 조회")
    @GetMapping("/user/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") Long userId) {
        List<GetOrderDto> result = this.orderService.getOrdersForUser(userId);
        return ResponseResult.ok(result).createResponseEntity();
    }
}

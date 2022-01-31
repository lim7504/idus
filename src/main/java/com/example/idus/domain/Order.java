package com.example.idus.domain;

import com.example.idus.domain.dto.GetOrderDto;
import com.example.idus.domain.dto.OrderDto;
import lombok.Getter;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

@Getter
@Entity
@Table(name = "idus_order")
public class Order extends CreatedModifiedAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderKey;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.orderKey = UUID.randomUUID().toString()
                .replace("-","")
                .toUpperCase(Locale.ROOT)
                .substring(0,12);
        order.user = orderDto.getUser();
        order.name = orderDto.getProductName();
        return order;
    }

    public GetOrderDto GetOrderListDto() {
        return GetOrderDto.builder()
                .id(this.id)
                .orderKey(this.orderKey)
                .name(this.name)
                .createdDate(this.createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

}

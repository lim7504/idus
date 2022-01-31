package com.example.idus.service;

import com.example.idus.config.Code;
import com.example.idus.config.IdusException;
import com.example.idus.domain.Order;
import com.example.idus.domain.User;
import com.example.idus.domain.dto.GetOrderDto;
import com.example.idus.domain.dto.OrderDto;
import com.example.idus.repository.OrderRepository;
import com.example.idus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    // 상품 주문
    @Transactional
    public void order(OrderDto orderDto) {
        User user = this.getUser(orderDto.getUserId());
        orderDto.setUser(user);
        Order order = Order.createOrder(orderDto);
        this.orderRepository.save(order);
    }

    // 유저별 주문 내역 조회
    @Transactional(readOnly = true)
    public List<GetOrderDto> getOrdersForUser(Long userId) {
        User user = this.getUser(userId);
        List<Order> orderList = this.getOrders(user);
        return orderList.stream()
                .map(Order::GetOrderListDto)
                .collect(Collectors.toList());
    }


    private List<Order> getOrders(User user) {
        return this.orderRepository.findByUserOrderByCreatedDateDesc(user);
    }


    private User getUser(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new IdusException(Code.ACCOUNT_NOT_FOUND));
    }

}

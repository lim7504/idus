package com.example.idus.repository;

import com.example.idus.domain.Order;
import com.example.idus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserOrderByCreatedDateDesc(User user);
}

package com.sparta.orderapi.repository;

import com.sparta.orderapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Order findByUserId(Long userId);
}

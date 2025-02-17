package com.sparta.orderapi.service;

import com.sparta.orderapi.model.Order;
import com.sparta.orderapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private OrderRepository orderRepository;

  public void createOrder(Order order) {
    orderRepository.save(order);
  }

  public Order getOrderById(Long id) {
    return orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found"));
  }
}

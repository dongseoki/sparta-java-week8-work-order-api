package com.sparta.orderapi.service;

import com.sparta.orderapi.model.Order;
import com.sparta.orderapi.repository.OrderRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final MeterRegistry meterRegistry;
  private Counter orderCounter;

  @PostConstruct
  private void initMetrics() {
    this.orderCounter = Counter.builder("order.created.count")
        .description("Number of created orders")
        .register(meterRegistry);
  }


  public void createOrder(Order order) {
    orderRepository.save(order);
    orderCounter.increment();
  }

  public Order getOrderById(Long id) {
    return orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found"));
  }
}

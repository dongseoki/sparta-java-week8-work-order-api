package com.sparta.orderapi.event;

public class CreateOrderEvent {
  private Long id;
  private Long userId;
  private Long productId;
  private Integer quantity;
  private String status;  // PENDING, COMPLETED, CANCELLED
}

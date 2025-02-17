package com.sparta.orderapi.event;

import com.sparta.orderapi.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateOrderEvent {
  private Long id;
  private Long userId;
  private Long productId;
  private Integer quantity;
  private String status;  // PENDING, COMPLETED, CANCELLED

  public static CreateOrderEvent of(Order order) {
    return CreateOrderEvent.builder()
        .id(order.getId())
        .userId(order.getUserId())
        .productId(order.getProductId())
        .quantity(order.getQuantity())
        .status(order.getStatus())
        .build();
  }
}

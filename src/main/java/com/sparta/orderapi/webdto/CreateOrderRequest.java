package com.sparta.orderapi.webdto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@ToString
public class CreateOrderRequest {

  private Long userId;
  private Long productId;
  private Integer quantity;
  private String status;  // PENDING, COMPLETED, CANCELLED
}

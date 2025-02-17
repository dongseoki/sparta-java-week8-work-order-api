package com.sparta.orderapi.model;

import com.sparta.orderapi.webdto.CreateOrderRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String status;  // PENDING, COMPLETED, CANCELLED

    public static Order of(CreateOrderRequest createOrderRequest) {
        return Order.builder()
            .userId(createOrderRequest.getUserId())
            .productId(createOrderRequest.getProductId())
            .quantity(createOrderRequest.getQuantity())
            .status(createOrderRequest.getStatus())
            .build();
    }
}

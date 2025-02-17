package com.sparta.orderapi.event;

import lombok.Data;

@Data
public class OrderEvent {
    private String eventId;
    private Long orderId;
    private String userId;
    private String status;
}


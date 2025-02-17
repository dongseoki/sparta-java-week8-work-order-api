package com.sparta.orderapi.controller;


import com.sparta.orderapi.event.OrderEventPublisher;
import com.sparta.orderapi.model.Order;
import com.sparta.orderapi.service.OrderService;
import com.sparta.orderapi.webdto.CreateOrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Autowired
    private OrderService orderService;

    @PostMapping
    @Operation(summary = "Create a new order")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {

        Order order = Order.of(createOrderRequest);
        order.setStatus("PENDING");
        orderService.createOrder(order);
        orderEventPublisher.publishOrderCreated(order.getId());

        return ResponseEntity.ok("Order Created: " + order.getId().toString());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}

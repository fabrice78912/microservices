package com.fabricedev.orderserv.controller;

import com.fabricedev.orderserv.dto.OrderRequest;
import com.fabricedev.orderserv.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder (@RequestBody OrderRequest orderRequest){
        orderService.placedOrder(orderRequest);
        return "Order placed successfully";
    }
}

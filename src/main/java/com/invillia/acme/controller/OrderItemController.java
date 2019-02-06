package com.invillia.acme.controller;

import com.invillia.acme.model.OrderItem;
import com.invillia.acme.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItem save(@RequestBody OrderItem orderItem){
        return orderItemService.save(orderItem);
    }
}

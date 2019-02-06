package com.invillia.acme.controller;

import com.invillia.acme.model.Order;
import com.invillia.acme.model.constant.OrderStatus;
import com.invillia.acme.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping
    public Page<Order> findAll(
            @RequestParam(value = "confirmationDate", required = false) LocalDateTime confirmationDate,
            @RequestParam(value = "status", required = false) OrderStatus status,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size) {

        return orderService.findAll(confirmationDate, status, address, page, size);
    }
}

package com.invillia.acme.service;

import com.invillia.acme.model.Order;
import com.invillia.acme.model.constant.OrderStatus;
import com.invillia.acme.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Page<Order> findAll(LocalDateTime confirmationDate, OrderStatus status, String address, Integer page, Integer size) {
        return orderRepository.findAllFiltering(confirmationDate, status, address, page, size);
    }
}

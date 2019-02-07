package com.invillia.acme.service;

import com.invillia.acme.model.OrderItem;
import com.invillia.acme.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}

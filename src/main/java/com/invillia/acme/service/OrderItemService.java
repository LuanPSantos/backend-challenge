package com.invillia.acme.service;

import com.invillia.acme.model.OrderItem;
import com.invillia.acme.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public Page<OrderItem> findAllFiltering(String description, Integer page, Integer size){
        return orderItemRepository.findAllFiltering(description, page, size);
    }
}

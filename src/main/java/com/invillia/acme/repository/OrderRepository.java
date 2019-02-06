package com.invillia.acme.repository;

import com.invillia.acme.model.Order;
import com.invillia.acme.model.constant.OrderStatus;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    default Page<Order> findAllFiltering(LocalDateTime confirmationDate, OrderStatus status, String address, Integer page, Integer size){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withMatcher("confirmationDate", exact())
                .withMatcher("status", exact().ignoreCase())
                .withMatcher("address", contains().ignoreCase())
                .withIgnoreNullValues();

        Example<Order> orderExample = Example.of(
                new Order()
                        .setStatus(status)
                        .setAddress(address)
                        .setConfirmationDate(confirmationDate), matcher);

        return this.findAll(orderExample, PageRequest.of(page, size));
    }
}

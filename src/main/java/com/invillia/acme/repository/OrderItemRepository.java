package com.invillia.acme.repository;

import com.invillia.acme.model.OrderItem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    default Page<OrderItem> findAllFiltering(String description, Integer page, Integer size){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withMatcher("description", contains().ignoreCase())
                .withIgnoreNullValues();

        Example<OrderItem> example = Example.of(
                new OrderItem()
                    .setDescription(description)
                , matcher);

        return this.findAll(example, PageRequest.of(page, size));
    }
}

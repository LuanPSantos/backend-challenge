package com.invillia.acme.repository;

import com.invillia.acme.model.Store;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    default Page<Store> findAllFiltering(String name, String address, Integer page, Integer size) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("address", contains().ignoreCase())
                .withIgnoreNullValues();

        Example<Store> stringExample = Example.of(
                new Store()
                        .setAddress(address)
                        .setName(name), matcher);

        return this.findAll(stringExample, PageRequest.of(page, size));
    }
}

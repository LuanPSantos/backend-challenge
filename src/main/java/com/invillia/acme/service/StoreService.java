package com.invillia.acme.service;

import com.invillia.acme.model.Store;
import com.invillia.acme.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public Store findById(Long id) {
        return storeRepository.findById(id).get();
    }

    public Page<Store> findAll(String name, String address, Integer page, Integer size) {
        return storeRepository.findAllFiltering(name, address, page, size);
    }
}

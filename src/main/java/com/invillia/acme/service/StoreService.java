package com.invillia.acme.service;

import com.invillia.acme.model.Store;
import com.invillia.acme.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store save(Store store){
        return storeRepository.save(store);
    }
}

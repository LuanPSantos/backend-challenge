package com.invillia.acme.controller;

import com.invillia.acme.model.Store;
import com.invillia.acme.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Store save(@RequestBody @Valid Store store){
        return storeService.save(store);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Store update(
            @PathVariable("id") Long id,
            @RequestBody @Valid Store store){

        store.setId(id);

        return storeService.save(store);
    }

    @GetMapping("{id}")
    public Store findById(
            @PathVariable("id") Long id){

        return storeService.findById(id);
    }

    @GetMapping
    public Page<Store> findAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size){

        return storeService.findAll(name, address, page, size);
    }
}

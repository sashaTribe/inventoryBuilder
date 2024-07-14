package com.sasha.sakiladb.controllers;

import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.entities.Store;
import com.sasha.sakiladb.output.InventoryResponse;
import com.sasha.sakiladb.output.StoreResponse;
import com.sasha.sakiladb.repository.StoreRepository;
import com.sasha.sakiladb.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/stores")
@CrossOrigin("*")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping
    public List<StoreResponse> getAllFilmsInStores() {
        List<Store> stores = this.storeService.findAll();
        return stores.stream().map(StoreResponse::new).toList();
    }


    @GetMapping("/{id}")
    public StoreResponse getStoreById(@PathVariable Short id) {
        return this.storeRepository.findById(id)
                .map(StoreResponse::new)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No such store with id %d",id)
                        ));
    }

    /*
    @GetMapping
    public List<Inventory> findFilmAvailability(@PathVariable String name){
        return this.storeService.findByFilmName(name);
    }

     */

}

package com.sasha.sakiladb.services;

import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.entities.Store;
import com.sasha.sakiladb.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public List<Store> findAll(){
        return storeRepository.findAll();
    }

    public List<Inventory> findByFilmName(String name){
        return storeRepository.findByInventoryLocationsFilmFilmNameContainingIgnoreCase(name);
    }
    public int getFilmCount(String name){
        return storeRepository.countByInventoryLocationsFilmFilmName(name);
    }

}

package com.sasha.sakiladb.services;

import com.sasha.sakiladb.entities.*;
import com.sasha.sakiladb.input.ActorInput;
import com.sasha.sakiladb.input.InventoryFormInput;
import com.sasha.sakiladb.input.InventoryInput;
import com.sasha.sakiladb.repository.FilmRepository;
import com.sasha.sakiladb.repository.InventoryRepository;
import com.sasha.sakiladb.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InventoryServices {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    FilmRepository filmRepository;

    public List<Inventory> findAll(){
        return inventoryRepository.findAll();
    }

    public List<Inventory> findByName(String name){
        return inventoryRepository.findByFilmFilmNameContainingIgnoreCase(name);
    }

    public int getFilmCount(String name, Short storeId){
        return inventoryRepository.countByFilmFilmNameAndStoreId(name,storeId);
    }

    public Inventory addToInventory(InventoryFormInput data){
        Inventory inventory = new Inventory();
        //inventory.setFilmName(data.getFilmName());
        if (data.getFilmId() != 0){
            Film film = filmRepository.findById(data.getFilmId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not get film object"));
            inventory.setFilm(film);
        }
        if(data.getStoreId() != 0){
            Store store = storeRepository.findById(data.getStoreId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            inventory.setStore(store);
        }
        Inventory created = inventory;
        return created;
    }

    public Inventory editInventory(int id, InventoryFormInput data){
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such stock with id %d", id)
                ));
        if (data.getFilmId() != 0) {
            Film film = filmRepository.findById(data.getFilmId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            inventory.setFilm(film);
        }
        if (data.getStoreId() != null) {
            Store store = storeRepository.findById(data.getStoreId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            inventory.setStore(store);
        }
        Inventory created = inventory;
        return inventory;
    }



}

package com.sasha.sakiladb.controllers;

import com.sasha.sakiladb.entities.Actor;
import com.sasha.sakiladb.entities.Film;
import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.input.ActorInput;
import com.sasha.sakiladb.input.InventoryFormInput;
import com.sasha.sakiladb.input.InventoryInput;
import com.sasha.sakiladb.input.ValidationGroup;
import com.sasha.sakiladb.output.ActorResponse;
import com.sasha.sakiladb.output.FilmResponse;
import com.sasha.sakiladb.output.InventoryResponse;
import com.sasha.sakiladb.repository.InventoryRepository;
import com.sasha.sakiladb.services.ActorService;
import com.sasha.sakiladb.services.InventoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryServices inventoryServices;


    @GetMapping("/{id}")
    public InventoryResponse getInventoryById(@RequestBody int id) {
        return this.inventoryRepository.findById(id)
                .map(InventoryResponse::new)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No such inventory with id %d",id)
                        ));
    }


    @GetMapping
    public List<InventoryResponse> getLocations(@RequestParam(required=false) Optional<String> name){

        return name.map(value -> inventoryServices.findByName(value))
                .orElseGet(() -> inventoryServices.findAll())
                .stream()
                .map(InventoryResponse::new)
                .toList();
    }


    @GetMapping("/availability")
    public String getFilmAvailabilityFromLocation(@RequestBody InventoryInput data) {
        return "There are " +
                this.inventoryServices.getFilmCount(data.getFilmName(), data.getStoreId())
                + " copies left in store " + data.getStoreId() ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse addToInventory(@Validated(ValidationGroup.Create.class) @RequestBody InventoryFormInput data){
        Inventory created = inventoryServices.addToInventory(data);
        return new InventoryResponse(created);
    }

    @PatchMapping("/{id}")
    public InventoryResponse editInventory(@PathVariable int id, @RequestBody InventoryFormInput data){
        Inventory created = inventoryServices.editInventory(id, data);
        return new InventoryResponse(created);

    }

    /**
     * TO-DO:
     * - Create model of rental
     * - delete any records of rental associating with inventory*/
    @DeleteMapping("/{id}")
    public void removeItemFromInventory(@PathVariable int id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such record with id %d",id)
                ));
        inventoryRepository.delete(inventory);
    }


}

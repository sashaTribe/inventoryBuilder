package com.sasha.sakiladb.controllers;

import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.entities.Rental;
import com.sasha.sakiladb.input.InventoryFormInput;
import com.sasha.sakiladb.input.InventoryInput;
import com.sasha.sakiladb.input.ValidationGroup;
import com.sasha.sakiladb.output.InventoryResponse;
import com.sasha.sakiladb.repository.InventoryRepository;
import com.sasha.sakiladb.repository.RentalRepository;
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
@CrossOrigin("*")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryServices inventoryServices;

    @Autowired
    private RentalRepository rentalRepository;


    @GetMapping("/{id}")
    public InventoryResponse getInventoryById(@RequestBody Long id) {
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

    @PostMapping(value = "/", consumes = {"*/*"})
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse addToInventory(@Validated(ValidationGroup.Create.class) @RequestBody InventoryFormInput data){
        Inventory created = inventoryServices.addToInventory(data);
        return new InventoryResponse(created);
        //sasha smells
    }

    @PatchMapping("/{id}")
    public InventoryResponse editInventory(@PathVariable Long id, @RequestBody InventoryFormInput data){
        Inventory created = inventoryServices.editInventory(id, data);
        return new InventoryResponse(created);

    }

    /**
     * TO-DO:
     * - Create model of rental
     * - delete any records of rental associating with inventory*/
    @DeleteMapping("/{id}")
    public void removeItemFromInventory(@PathVariable Long id) {
        //Rental rental = rentalRepository.findByInventoryId(Id)
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such record with id %d",id)
                ));
        inventoryRepository.delete(inventory);
    }


}

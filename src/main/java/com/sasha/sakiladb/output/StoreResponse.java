package com.sasha.sakiladb.output;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sasha.sakiladb.entities.Address;
import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.entities.Store;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StoreResponse {
    private final Short id;
    private final long filmStock;
    private final String address;
    private final String city;
    private final String country;


    public StoreResponse(Store store){
        this.id = store.getId();
        this.filmStock = store.getInventoryLocations().stream().count();
        this.address= store.getAddress().getAddress();
        this.city = store.getAddress().getCity().getCity();
        this.country = store.getAddress().getCity().getCountry().getCountryName();
    }




}

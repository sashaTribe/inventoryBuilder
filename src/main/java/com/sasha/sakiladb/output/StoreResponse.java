package com.sasha.sakiladb.output;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sasha.sakiladb.entities.Address;
import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.entities.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StoreResponse {
    @JsonProperty("id")
    private final Short id;
    @JsonProperty("filmStock")
    private final long filmStock;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("city")
    private final String city;
    @JsonProperty("country")
    private final String country;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public StoreResponse(Store store){
        this.id = store.getId();
        this.filmStock = store.getInventoryLocations().stream().count();
        this.address= store.getAddress().getAddress();
        this.city = store.getAddress().getCity().getCity();
        this.country = store.getAddress().getCity().getCountry().getCountryName();
    }




}

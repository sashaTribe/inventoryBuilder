package com.sasha.sakiladb.output;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sasha.sakiladb.entities.Film;
import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.entities.Store;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class InventoryResponse {
    private final int id;
    private final String filmName;
    private final String address;

    public InventoryResponse(Inventory inventory){
        this.id = inventory.getId();
        this.filmName = inventory.getFilm().getFilmName();
        this.address = inventory.getStore().getAddress().getAddress();
    }

}

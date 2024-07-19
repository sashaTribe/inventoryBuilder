package com.sasha.sakiladb.output;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.sasha.sakiladb.entities.Film;
import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.entities.Store;
import lombok.Getter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class InventoryResponse {
    private final Long id;
    private final String filmName;
    private final String address;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InventoryResponse(Inventory inventory){
        this.id = inventory.getId();
        this.filmName = inventory.getFilm().getFilmName();
        this.address = inventory.getStore().getAddress().getAddress();
    }

}

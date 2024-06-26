package com.sasha.sakiladb.output;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sasha.sakiladb.entities.Inventory;

/**
 * TO-DO:
 * - Make a query request where user types in film and it shows where it is available
 * - Pagination
 * - Make tests on Post Man
 * */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class InventoryReferenceResponse {
    private final String filmName;

    public InventoryReferenceResponse (Inventory inventory){
        this.filmName = inventory.getFilm().getFilmName();
    }
}

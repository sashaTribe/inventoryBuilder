package com.sasha.sakiladb.output;


import com.sasha.sakiladb.entities.Film;
import lombok.Data;

@Data
public class FilmInventoryOutput {
    private String filmName;
    private int quantity;
    private Short store_id;

    public FilmInventoryOutput(Film film) {
        this.filmName = film.getFilmName();
        this.store_id = film.getInventoryLocations().getFirst().getStore().getId();

    }
}

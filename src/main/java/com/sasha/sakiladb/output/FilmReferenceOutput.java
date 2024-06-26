package com.sasha.sakiladb.output;

import com.sasha.sakiladb.entities.Actor;
import com.sasha.sakiladb.entities.Film;
import lombok.Data;
import lombok.Getter;

@Data
public class FilmReferenceOutput {
    private String filmName;

    public FilmReferenceOutput(Film film) {
        this.filmName = film.getFilmName();
    }
}
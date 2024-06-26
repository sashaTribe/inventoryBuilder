package com.sasha.sakiladb.output;

import com.sasha.sakiladb.entities.Actor;
import com.sasha.sakiladb.entities.Film;
import lombok.Getter;

import java.time.Year;
import java.util.List;

@Getter
public class FilmResponse {
    private final int id;
    private final String filmName;
    private String description;
    private int releaseYear;
    private Short length;
    private List<ActorReferenceOutput> cast;

    //private final Byte languageId;
    private String language;


    public FilmResponse(Film film){
        this.id = film.getId();
        this.filmName = film.getFilmName();
        this.language = film.getLanguage().getName();
        this.cast = film.getCast()
                .stream()
                .map(ActorReferenceOutput::new)
                .toList();
        this.releaseYear = film.getReleaseYear();
        this.length = film.getLength();

    }


}

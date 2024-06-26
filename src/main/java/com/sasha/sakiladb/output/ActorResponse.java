package com.sasha.sakiladb.output;

import com.sasha.sakiladb.entities.Actor;
import com.sasha.sakiladb.entities.Film;
import lombok.Getter;

import java.util.List;

@Getter
public class ActorResponse {
    private final Short id;
    private final String firstName;
    private final String lastName;
    private final List<FilmReferenceOutput> films;

    public ActorResponse(Actor actor){
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.films = actor.getFilms()
                .stream()
                .map(FilmReferenceOutput::new)
                .toList();
    }


}

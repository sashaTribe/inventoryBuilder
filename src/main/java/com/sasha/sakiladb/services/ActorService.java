package com.sasha.sakiladb.services;

import com.sasha.sakiladb.entities.Actor;
import com.sasha.sakiladb.entities.Film;
import com.sasha.sakiladb.entities.Language;
import com.sasha.sakiladb.input.ActorInput;
import com.sasha.sakiladb.input.FilmInput;
import com.sasha.sakiladb.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public Actor createActor(ActorInput data){
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());

        Actor created = actorRepository.save(actor);
        return created;
    }

    public List<Actor> findAll(){
        return actorRepository.findAll();
    }

    public List<Actor> findByName(String name){
        return actorRepository.findByFullNameContainingIgnoreCase(name);
    }

    public Actor updateRecord(Short id, ActorInput data) {
        Actor actor = actorRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such actor with id %d", id)
                ));
        if (data.getFirstName() != null) {
            actor.setFirstName(data.getFirstName());
        }
        if (data.getLastName() != null) {
            actor.setLastName(data.getLastName());
        }
        Actor created = actorRepository.save(actor);
        return created;
    }
}

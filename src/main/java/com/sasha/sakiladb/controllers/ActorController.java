package com.sasha.sakiladb.controllers;

import com.sasha.sakiladb.input.ActorInput;
import com.sasha.sakiladb.input.ValidationGroup;
import com.sasha.sakiladb.output.ActorResponse;
import com.sasha.sakiladb.repository.ActorRepository;
import com.sasha.sakiladb.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.sasha.sakiladb.services.ActorService;

import com.sasha.sakiladb.entities.Actor;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;


    @GetMapping
    public List<ActorResponse> getActors(@RequestParam(required=false) Optional<String> name){
        return name.map(value -> actorService.findByName(value))
                .orElseGet(() -> actorService.findAll())
                .stream()
                .map(ActorResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ActorResponse getActorById(@PathVariable Short id) {
        return actorRepository.findById(id)
                .map(ActorResponse::new)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No such actor with id %d",id)
                        ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorResponse createActor(@Validated(ValidationGroup.Create.class) @RequestBody ActorInput data){
        Actor created = actorService.createActor(data);
        return new ActorResponse(created);
    }

    @DeleteMapping("/{id}")
    public void removeActor(@PathVariable Short id) {
        actorRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No such actor with id %d",id)
                        ));
        actorRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public ActorResponse updateRecord(@PathVariable Short id, @RequestBody ActorInput data){
        Actor created = actorService.updateRecord(id,data);
        return new ActorResponse(created);

    }







}

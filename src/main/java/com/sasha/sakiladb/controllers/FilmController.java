package com.sasha.sakiladb.controllers;


import com.sasha.sakiladb.entities.Film;
import com.sasha.sakiladb.entities.Language;
import com.sasha.sakiladb.input.FilmInput;
import com.sasha.sakiladb.input.ValidationGroup;
import com.sasha.sakiladb.output.FilmResponse;
import com.sasha.sakiladb.repository.FilmRepository;
import com.sasha.sakiladb.repository.LanguageRepository;
import com.sasha.sakiladb.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    FilmService filmService;

    @GetMapping
    public List<FilmResponse> getFilms(@RequestParam(required=false) Optional<String> name){
        return name.map(value -> filmService.findByName(value))
                .orElseGet(() -> filmService.findAll())
                .stream()
                .map(FilmResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public FilmResponse getFilmById(@PathVariable int id) {
        return filmRepository.findById(id)
                .map(FilmResponse::new)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No such film with id %d",id)
                        ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmResponse createFilm(@Validated(ValidationGroup.Create.class) @RequestBody FilmInput data){
        Film created = filmService.createFilm(data);
        return new FilmResponse(created);
    }

    @DeleteMapping("/{id}")
    public void removeFilm(@PathVariable int id) {
        Film film = filmRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such film with id %d",id)
                ));
        filmRepository.delete(film);
    }

    @PatchMapping("/{id}")
    public FilmResponse updateRecord(@PathVariable int id, @RequestBody FilmInput data){
        Film created = filmService.updateRecord(id, data);
        return new FilmResponse(created);

    }



}

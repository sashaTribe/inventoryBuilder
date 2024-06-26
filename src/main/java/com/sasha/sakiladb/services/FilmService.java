package com.sasha.sakiladb.services;

import com.sasha.sakiladb.entities.Film;
import com.sasha.sakiladb.entities.Language;
import com.sasha.sakiladb.input.FilmInput;
import com.sasha.sakiladb.output.FilmResponse;
import com.sasha.sakiladb.repository.FilmRepository;
import com.sasha.sakiladb.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public List<Film> findAll() {
        return filmRepository.findAll();
    }


    public List<Film> findByName(String name) {
        return filmRepository.findByFilmNameContainingIgnoreCase(name);
    }


    public Film createFilm(FilmInput data){
        Film film = new Film();
        film.setFilmName(data.getFilmName());
        if (data.getLanguageId() != null){
            Language language = languageRepository.findById(data.getLanguageId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            film.setLanguage(language);
        }
        /*
        if (data.getLength() != 0) {
            film.setLength(data.getLength());
        }
        if (data.getReleaseYear() != 0) {
            film.setReleaseYear(data.getReleaseYear());
        }
        if (data.getRentalDuration() != 0) {
            film.setRentalDuration(data.getRentalDuration());
        }
        if (data.getSpecialFeatures() != null) {
            film.setSpecialFeatures(data.getSpecialFeatures());
        }

         */
        Film created = film;
        return created;
    }

    public Film updateRecord(int id,FilmInput data) {
        Film film = filmRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such actor with id %d", id)
                ));
        if (data.getFilmName() != null) {
            film.setFilmName(data.getFilmName());
        }
        /*
        if (data.getLength() != null) {
            film.setLength(data.getLength());
        }
        if (data.getReleaseYear() != 0) {
            film.setReleaseYear(data.getReleaseYear());
        }
        if (data.getRentalDuration() != null) {
            film.setRentalDuration(data.getRentalDuration());
        }
        if (data.getSpecialFeatures() != null) {
            film.setSpecialFeatures(data.getSpecialFeatures());
        }

         */
        if (data.getLanguageId() != null) {
            Language language = languageRepository.findById(data.getLanguageId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            film.setLanguage(language);
        }


        Film created = film;
        return created;
    }

}

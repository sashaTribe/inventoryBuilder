package com.sasha.sakiladb.repository;
import com.sasha.sakiladb.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findByFilmNameContainingIgnoreCase(String name);
}
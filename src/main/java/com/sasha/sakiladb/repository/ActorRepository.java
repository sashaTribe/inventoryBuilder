package com.sasha.sakiladb.repository;
import com.sasha.sakiladb.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sasha.sakiladb.entities.Actor;
import java.util.List;


public interface ActorRepository extends JpaRepository<Actor, Short> {
    List<Actor> findByFullNameContainingIgnoreCase(String name);
}
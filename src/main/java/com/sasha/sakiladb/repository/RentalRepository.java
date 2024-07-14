package com.sasha.sakiladb.repository;

import com.sasha.sakiladb.entities.Rental;
import com.sasha.sakiladb.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

}

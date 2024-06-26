package com.sasha.sakiladb.repository;

import com.sasha.sakiladb.entities.Inventory;
import com.sasha.sakiladb.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Short> {
    List<Inventory> findByInventoryLocationsFilmFilmNameContainingIgnoreCase(String name);
    int countByInventoryLocationsFilmFilmName(String filmName);
}

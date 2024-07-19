package com.sasha.sakiladb.repository;

import com.sasha.sakiladb.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByFilmFilmNameContainingIgnoreCase(String name);

    //@Query("SELECT COUNT(film_id,store_id) FROM inventory ")
    int countByFilmFilmNameAndStoreId(String filmName, Short id);
}

package com.sasha.sakiladb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="inventory")
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="inventory_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="inventory")
    private List<Rental> rentals = new ArrayList<>();


}

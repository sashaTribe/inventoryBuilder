package com.sasha.sakiladb.entities;
import com.sasha.sakiladb.dataType.Rating;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Entity
@Table(name="Film")
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int id;

    @Column(name="title")
    private String filmName;


    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = { @JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private List<Actor> cast = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="film_id")
    private List<Inventory> inventoryLocations = new ArrayList<>();

    @Column(name="release_year")
    private int releaseYear = 0;

    @Column(name="rental_duration")
    private short rentalDuration = 0;

    @Column(name = "rental_rate")
    private Double rentalRate = 0.0;

    @Column(name="length")
    private Short length = 0;

/*
    @Column(name="rating")
    private Rating rating;

 */

@Column(name="special_features")
    private String specialFeatures;


    @ManyToOne
    @JoinColumn(name="language_id")
    private Language language;



}


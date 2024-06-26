package com.sasha.sakiladb.entities;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="country_id")
    private Short id;

    @Column(name="country")
    private String countryName;
}

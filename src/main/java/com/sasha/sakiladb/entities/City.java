package com.sasha.sakiladb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private Short id;

    @Column(name="city")
    private String city;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

}

package com.sasha.sakiladb.entities;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Short id;

    @Column(name="address")
    private String address;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;


}

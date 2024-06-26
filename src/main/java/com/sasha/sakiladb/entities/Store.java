package com.sasha.sakiladb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="store_id")
    private Short id;

    @OneToMany
    @JoinColumn(name="store_id")
    private List<Inventory> inventoryLocations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;


}

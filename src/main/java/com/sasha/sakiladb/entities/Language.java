package com.sasha.sakiladb.entities;

import jakarta.persistence.*;
import lombok.Getter;


@Table(name = "language")
@Getter
@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Byte id;

    @Column(name = "name")
    private String name;
}

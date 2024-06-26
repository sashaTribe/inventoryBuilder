package com.sasha.sakiladb.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "actor")
@Setter
@Getter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    @Getter
    private Short id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    public String getFullname(){
        return this.firstName + " " + this.lastName;
    }

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = { @JoinColumn(name = "actor_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")}
    )
    private List<Film> films = new ArrayList<>();

    @Formula("concat(first_name, ' ',last_name) ")
    private String fullName;


}

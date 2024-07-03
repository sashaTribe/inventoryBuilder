package com.sasha.sakiladb.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="rental")
@Getter
public class Rental {

    @ManyToOne
    @JoinColumn(name="inventory_id")
    private Inventory inventory;
}

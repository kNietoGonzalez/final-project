package com.example.projekt_koncowy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Ingredient {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String quantity;

    @ManyToMany
    private Set<Recipe> recipes;
}

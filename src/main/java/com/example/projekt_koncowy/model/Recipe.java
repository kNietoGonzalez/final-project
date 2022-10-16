package com.example.projekt_koncowy.model;

import com.example.projekt_koncowy.enums.TypeOfCuisine;
import com.example.projekt_koncowy.enums.TypeOfDish;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Recipe {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double rating;

   // @Column
   // private boolean favourite;

    @Column
    private String estimation;

    @Column(name = "type_of_dish")
    @Enumerated(value = EnumType.STRING)
    private TypeOfDish typeOfDish;

    @Column(name = "type_of_cuisine")
    @Enumerated(value = EnumType.STRING)
    private TypeOfCuisine typeOfCuisine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn (name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;
}

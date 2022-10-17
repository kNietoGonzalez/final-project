package com.example.projekt_koncowy.dto;


import com.example.projekt_koncowy.enums.TypeOfCuisine;
import com.example.projekt_koncowy.enums.TypeOfDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private int id;

    private String name;

    private String description;

    private double rating;

    //private boolean favourite;

    private String estimation;

    private int userId;

    private TypeOfDish typeOfDish;

    private TypeOfCuisine typeOfCuisine;

    private Set<Integer> ingredients;
}

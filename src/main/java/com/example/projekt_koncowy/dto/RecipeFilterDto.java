package com.example.projekt_koncowy.dto;

import com.example.projekt_koncowy.model.Ingredient;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class RecipeFilterDto {
    private String name;

    private Integer typeOfDish;

    private Integer typeOfCuisine;

    private Integer user ;

    private Set<Integer> ingredients;
}

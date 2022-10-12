package com.example.projekt_koncowy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    private String name;

    private String email;

    private String password;

    private List<RecipeDto> recipeList;

}

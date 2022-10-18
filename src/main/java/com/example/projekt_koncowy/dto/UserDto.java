package com.example.projekt_koncowy.dto;

import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(groups = IUpdateRequestValidation.class)
    private int id;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String name;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String email;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String password;

    private List<RecipeDto> recipeList;

}

package com.example.projekt_koncowy.dto;

import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(groups = IUpdateRequestValidation.class)
    private int id;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @Size (min = 5, groups = {ICreateRequestValidation.class, IUpdateRequestValidation.class})
    private String name;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String email;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @Size (min = 8, groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String password;

    private List<RecipeDto> recipeList;

}

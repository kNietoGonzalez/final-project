package com.example.projekt_koncowy.dto;

import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    @NotNull(groups = IUpdateRequestValidation.class)
    @NotEmpty(groups = IUpdateRequestValidation.class)
    private Integer id;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String name;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String description;


    private double rating;

    //private boolean favourite;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String estimation;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private Integer userId;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String typeOfDish;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String typeOfCuisine;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private Set<Integer> ingredients;

}

package com.example.projekt_koncowy.dto;

import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfDishDto {

    @NotNull(groups = IUpdateRequestValidation.class)
    private int id;

    @NotNull(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    @NotEmpty(groups = {IUpdateRequestValidation.class, ICreateRequestValidation.class})
    private String name;
}

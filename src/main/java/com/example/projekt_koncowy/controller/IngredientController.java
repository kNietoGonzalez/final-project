package com.example.projekt_koncowy.controller;

import com.example.projekt_koncowy.dto.IngredientDto;
import com.example.projekt_koncowy.repository.IngredientRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepositoryImpl ingredientRepositoryImpl;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IngredientDto> findAll() {
        return ingredientRepositoryImpl.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto findById(@PathVariable("id") Integer id) {
        return ingredientRepositoryImpl.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer createIngredient(@RequestBody IngredientDto ingredientDto) {
        return ingredientRepositoryImpl.createIngredient(ingredientDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        ingredientRepositoryImpl.delete(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto update(@PathVariable("id") Integer id, @RequestBody IngredientDto ingredientDto) {
        ingredientDto.setId(id);
        return ingredientRepositoryImpl.update(ingredientDto);
    }
}

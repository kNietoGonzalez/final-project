package com.example.projekt_koncowy.controller;

import com.example.projekt_koncowy.dto.RecipeDto;
import com.example.projekt_koncowy.repository.RecipeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepositoryImpl recipeRepositoryImpl;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List <RecipeDto> findAll(){
        return recipeRepositoryImpl.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipeDto findById(@PathVariable("id") Integer id){
        return recipeRepositoryImpl.findById(id);
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer createRecipe(@RequestBody RecipeDto recipeDto){
        return recipeRepositoryImpl.createRecipe(recipeDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id){
        recipeRepositoryImpl.delete(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public RecipeDto update(@PathVariable("id") Integer id, @RequestBody RecipeDto recipeDto){
        recipeDto.setId(id);
        return recipeRepositoryImpl.update(recipeDto);
    }
}

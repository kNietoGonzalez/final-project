package com.example.projekt_koncowy.controller;

import com.example.projekt_koncowy.dto.RecipeDto;
import com.example.projekt_koncowy.exceptions.UserNotFoundException;
import com.example.projekt_koncowy.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@Slf4j
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeDto>> findAll() {
        return new ResponseEntity<>(recipeService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDto> findById(@PathVariable("id") Integer id) {
            return new ResponseEntity<>(recipeService.findById(id), HttpStatus.OK);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createRecipe(@RequestBody RecipeDto recipeDto) {
            return new ResponseEntity<>(recipeService.createRecipe(recipeDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        recipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDto> update(@PathVariable("id") Integer id, @RequestBody RecipeDto recipeDto) {
        recipeDto.setId(id);
        return new ResponseEntity<>(recipeService.update(recipeDto), HttpStatus.OK);
    }
}

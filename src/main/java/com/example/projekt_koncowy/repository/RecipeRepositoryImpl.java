package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.exceptions.NotFoundException;
import com.example.projekt_koncowy.model.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecipeRepositoryImpl {


    public static final String NOT_EXIST = "Recipe %d not found";
    private final IRecipeRepository recipeRepository;

    public Integer createRecipe(Recipe recipe) {
        Recipe save = recipeRepository.save(recipe);
        return save.getId();
    }

    @Transactional
    public Recipe findById(Integer id) {
        Optional<Recipe> saved = recipeRepository.findById(id);
        return saved.orElseThrow(() -> new NotFoundException(String.format(NOT_EXIST, id)));
    }

    @Transactional
    public List<Recipe> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        if (CollectionUtils.isEmpty(recipes)) {
            throw new NotFoundException("Recipe not found");
        }
        return recipes;
    }

    public void delete(Integer id) {
        try {
            recipeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            String error = String.format(NOT_EXIST, id);
            log.error(error);
            throw new NotFoundException(error);
        }
    }

    @Transactional
    public Recipe update(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}

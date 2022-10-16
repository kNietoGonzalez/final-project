package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.dto.RecipeDto;
import com.example.projekt_koncowy.model.Ingredient;
import com.example.projekt_koncowy.model.Recipe;
import com.example.projekt_koncowy.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class RecipeRepositoryImpl {

    private final IRecipeRepository recipeRepository;

    private final IUserRepository userRepository;

    private final IIngredientRepository ingredientRepository;

    public Integer createRecipe(RecipeDto recipeDto) {
        Optional<User> user = userRepository.findById(recipeDto.getUserId());
        if(user.isPresent()){
            Recipe recipe = new Recipe();
            recipe.setName(recipeDto.getName());
            recipe.setDescription(recipeDto.getDescription());
            recipe.setRating(recipeDto.getRating());
            recipe.setEstimation(recipeDto.getEstimation());
            recipe.setUser(user.get());
            recipe.setTypeOfDish(recipeDto.getTypeOfDish());
            recipe.setTypeOfCuisine(recipeDto.getTypeOfCuisine());
            recipe.setIngredients(mapIngredients(recipeDto.getIngredients()));
            Recipe save = recipeRepository.save(recipe);
            return save.getId();
        }
        return null;
    }

    private Set<Ingredient> mapIngredients(Set<Integer> ingredientsList){
        return ingredientsList
                .stream()
                .map(ingredient -> {
                    Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredient);
                    return optionalIngredient.orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public RecipeDto findById(Integer id) {
        Optional<Recipe> saved = recipeRepository.findById(id);
        if (saved.isPresent()) {
            Recipe recipe = saved.get();
            RecipeDto recipeDto = new RecipeDto();
            recipeDto.setId(recipe.getId());
            recipeDto.setName(recipe.getName());
            recipeDto.setDescription(recipe.getDescription());
            recipeDto.setRating(recipe.getRating());
            recipeDto.setEstimation(recipe.getEstimation());
            recipeDto.setUserId(recipe.getUser().getId());
            recipeDto.setTypeOfDish(recipe.getTypeOfDish());
            recipeDto.setTypeOfCuisine(recipe.getTypeOfCuisine());
            recipeDto.setIngredients(recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet()));
            return recipeDto;
        }
        return null;
    }

    public List<RecipeDto> findAll() {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> new RecipeDto(recipe.getId(), recipe.getName(),
                        recipe.getDescription(), recipe.getRating(), recipe.getEstimation(),
                        recipe.getUser().getId(), recipe.getTypeOfDish(), recipe.getTypeOfCuisine(),
                        recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        recipeRepository.deleteById(id);
    }

    public RecipeDto update (RecipeDto recipeDto){
        Optional<Recipe> saved = recipeRepository.findById(recipeDto.getId());
        if (saved.isPresent()){
            Recipe recipe = saved.get();
            recipe.setId(recipeDto.getId());
            recipe.setName(recipeDto.getName());
            recipe.setDescription(recipeDto.getDescription());
            recipe.setRating(recipeDto.getRating());
            recipe.setEstimation(recipeDto.getEstimation());
            recipe.setTypeOfDish(recipeDto.getTypeOfDish());
            recipe.setTypeOfCuisine(recipeDto.getTypeOfCuisine());
            recipe.getIngredients().addAll(addIngredients(recipeDto.getIngredients()));
            recipeRepository.save(recipe);
            return recipeDto;
        }
        return null;
    }

    private Set<Ingredient> addIngredients(Set<Integer> ingredients) {
        if (CollectionUtils.isEmpty(ingredients)) {
            return null;
        }
        return ingredients
                .stream()
                .map(ingredient -> {
                    Optional<Ingredient> repositoryById = ingredientRepository.findById(ingredient);
                    return repositoryById.orElse(null);
                }).filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}

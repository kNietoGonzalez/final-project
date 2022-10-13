package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.dto.RecipeDto;
import com.example.projekt_koncowy.model.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecipeRepositoryImpl {

    private final RecipeRepository recipeRepository;

    public Integer createRecipe(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setRating(recipeDto.getRating());
        recipe.setEstimation(recipeDto.getEstimation());
        recipe.setUserId(recipeDto.getUserId());
        recipe.setTypeOfDish(recipeDto.getTypeOfDish());
        recipe.setTypeOfCuisine(recipeDto.getTypeOfCuisine());
        Recipe save = recipeRepository.save(recipe);
        return save.getId();
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
            recipeDto.setUserId(recipe.getUserId());
            recipeDto.setTypeOfDish(recipe.getTypeOfDish());
            recipeDto.setTypeOfCuisine(recipe.getTypeOfCuisine());
            return recipeDto;
        }
        return null;
    }

    public List<RecipeDto> findAll() {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> new RecipeDto(recipe.getId(), recipe.getName(),
                        recipe.getDescription(), recipe.getRating(), recipe.getEstimation(),
                        recipe.getUserId(), recipe.getTypeOfDish(), recipe.getTypeOfCuisine()))
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
            recipe.setUserId(recipeDto.getUserId());
            recipe.setTypeOfDish(recipeDto.getTypeOfDish());
            recipe.setTypeOfCuisine(recipeDto.getTypeOfCuisine());
            return recipeDto;
        }
        return null;
    }
}

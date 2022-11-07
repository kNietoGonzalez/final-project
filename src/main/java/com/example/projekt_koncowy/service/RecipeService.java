package com.example.projekt_koncowy.service;

import com.example.projekt_koncowy.dto.RecipeDto;
import com.example.projekt_koncowy.dto.RecipeFilterDto;
import com.example.projekt_koncowy.model.*;
import com.example.projekt_koncowy.repository.*;
import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class RecipeService extends CommonService {

    @Getter
    private final Validator validator;
    private final UserRepositoryImpl userRepository;

    private final IngredientRepositoryImpl ingredientRepository;

    private final RecipeRepositoryImpl recipeRepository;

    private final TypeOfDishRepositoryImpl typeOfDishRepository;

    private final TypeOfCuisineRepositoryImpl typeOfCuisineRepository;

    public List<RecipeDto> findByFilter(RecipeFilterDto recipeFilterDto) {
        return recipeRepository.recipeFilter(recipeFilterDto).stream().map(recipe -> new RecipeDto(recipe.getId(), recipe.getName(),
                        recipe.getDescription(), recipe.getRating(), recipe.getEstimation(),
                        recipe.getUser().getId(), recipe.getTypeOfDish().getName(), recipe.getTypeOfCuisine().getName(),
                        recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    public Integer createRecipe(RecipeDto recipeDto) {
        validate(recipeDto, ICreateRequestValidation.class);
        User user = userRepository.findById(recipeDto.getUserId());
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setRating(recipeDto.getRating());
        recipe.setEstimation(recipeDto.getEstimation());
        recipe.setUser(user);
        TypeOfCuisine typeOfCuisine = typeOfCuisineRepository.findByName(recipeDto.getTypeOfCuisine());
        TypeOfDish typeOfDish = typeOfDishRepository.findByName(recipeDto.getTypeOfDish());
        recipe.setTypeOfDish(typeOfDish);
        recipe.setTypeOfCuisine(typeOfCuisine);
        recipe.setIngredients(mapIngredients(recipeDto.getIngredients()));
        return recipeRepository.createRecipe(recipe);
    }


    private Set<Ingredient> mapIngredients(Set<Integer> ingredientsList) {
        return ingredientsList
                .stream()
                .map(ingredientRepository::findById)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public RecipeDto findById(Integer id) {
        Recipe recipe = recipeRepository.findById(id);
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(recipe.getId());
        recipeDto.setName(recipe.getName());
        recipeDto.setDescription(recipe.getDescription());
        recipeDto.setRating(recipe.getRating());
        recipeDto.setEstimation(recipe.getEstimation());
        recipeDto.setUserId(recipe.getUser().getId());
        recipeDto.setTypeOfDish(recipe.getTypeOfDish().getName());
        recipeDto.setTypeOfCuisine(recipe.getTypeOfCuisine().getName());
        recipeDto.setIngredients(recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet()));
        return recipeDto;
    }

    public List<RecipeDto> findAll() {
        return recipeRepository.findAll()
                .stream()
                .map(recipe -> new RecipeDto(recipe.getId(), recipe.getName(),
                        recipe.getDescription(), recipe.getRating(), recipe.getEstimation(),
                        recipe.getUser().getId(), recipe.getTypeOfDish().getName(), recipe.getTypeOfCuisine().getName(),
                        recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    public List<RecipeDto> findByUser(Integer id) {
        return recipeRepository.findByUser(id)
                .stream()
                .map(recipe -> new RecipeDto(recipe.getId(), recipe.getName(),
                        recipe.getDescription(), recipe.getRating(), recipe.getEstimation(),
                        recipe.getUser().getId(), recipe.getTypeOfDish().getName(), recipe.getTypeOfCuisine().getName(),
                        recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());

    }

    public List<RecipeDto> findByTypeOfDish(Integer id) {
        return recipeRepository.findByTypeOfDish(id)
                .stream()
                .map(recipe -> new RecipeDto(recipe.getId(),
                        recipe.getName(), recipe.getDescription(), recipe.getRating(), recipe.getEstimation(),
                        recipe.getUser().getId(), recipe.getTypeOfDish().getName(), recipe.getTypeOfCuisine().getName(),
                        recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    public List<RecipeDto> findByTypeOfCuisine(Integer id) {
        return recipeRepository.findByTypeOfCuisine(id)
                .stream()
                .map(recipe -> new RecipeDto(recipe.getId(), recipe.getName(),
                        recipe.getDescription(), recipe.getRating(), recipe.getEstimation(), recipe.getUser().getId(),
                        recipe.getTypeOfDish().getName(), recipe.getTypeOfCuisine().getName(), recipe.getIngredients()
                        .stream().map(Ingredient::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        recipeRepository.delete(id);
    }

    public RecipeDto update(RecipeDto recipeDto) {
        validate(recipeDto, IUpdateRequestValidation.class);
        Recipe saved = recipeRepository.findById(recipeDto.getId());
        saved.setId(recipeDto.getId());
        saved.setName(recipeDto.getName());
        saved.setDescription(recipeDto.getDescription());
        saved.setRating(recipeDto.getRating());
        saved.setEstimation(recipeDto.getEstimation());
        TypeOfCuisine typeOfCuisine = typeOfCuisineRepository.findByName(recipeDto.getTypeOfCuisine());
        TypeOfDish typeOfDish = typeOfDishRepository.findByName(recipeDto.getTypeOfDish());
        saved.setTypeOfDish(typeOfDish);
        saved.setTypeOfCuisine(typeOfCuisine);
        saved.getIngredients().addAll(addIngredients(recipeDto.getIngredients()));
        Recipe updated = recipeRepository.update(saved);
        return new RecipeDto(updated.getId(), updated.getName(), updated.getDescription(), updated.getRating(),
                updated.getEstimation(), updated.getUser().getId(), updated.getTypeOfDish().getName(),
                updated.getTypeOfCuisine().getName(), updated.getIngredients().stream().map(Ingredient::getId)
                .collect(Collectors.toSet()));
    }

    private Set<Ingredient> addIngredients(Set<Integer> ingredients) {
        if (CollectionUtils.isEmpty(ingredients)) {
            return null;
        }
        return ingredients
                .stream()
                .map(ingredientRepository::findById)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}

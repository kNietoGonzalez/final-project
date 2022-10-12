package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.dto.IngredientDto;
import com.example.projekt_koncowy.model.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IngredientRepositoryImpl {

    private final IngredientRepository ingredientRepository;

    public Integer createIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDto.getName());
        ingredient.setQuantity(ingredientDto.getQuantity());
        Ingredient save = ingredientRepository.save(ingredient);
        return save.getId();
    }

    public IngredientDto findById(Integer id) {
        Optional<Ingredient> saved = ingredientRepository.findById(id);
        if (saved.isPresent()) {
            Ingredient ingredient = saved.get();
            IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setName(ingredient.getName());
            ingredientDto.setId(ingredient.getId());
            ingredientDto.setQuantity(ingredient.getQuantity());
            return ingredientDto;
        }
        return null;
    }

    public List<IngredientDto> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(ingredient -> new IngredientDto(ingredient.getId(), ingredient.getName(), ingredient.getQuantity()))
                .collect(Collectors.toList());
    }
    public void delete (Integer id){
        ingredientRepository.deleteById(id);
    }

    public IngredientDto update (IngredientDto ingredientDto){
        Optional<Ingredient> saved = ingredientRepository.findById(ingredientDto.getId());
        if (saved.isPresent()) {
            Ingredient ingredient = saved.get();
            ingredient.setName(ingredientDto.getName());
            ingredient.setQuantity(ingredientDto.getQuantity());
            ingredientRepository.save(ingredient);
            return ingredientDto;
        }
        return null;
    }
}
